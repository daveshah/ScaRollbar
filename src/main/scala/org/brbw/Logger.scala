package org.brbw

import java.util.concurrent.Executors

import scala.concurrent.{ExecutionContext, Future}
import scalaj.http.Http

case class Logger(apiKey: String, environment: String) {

    def log(throwable: Throwable): Throwable = {
        log(RollbarData(apiKey, environment, throwable))
        throwable
    }

    def log(message: String): Unit = {
        log(RollbarData(apiKey, environment, message))
    }

    private def log(data: RollbarData) : Unit = {
        val pool = Executors.newCachedThreadPool()
        implicit val context = ExecutionContext.fromExecutor(pool)
        val f = Future {
            Http.postData("https://api.rollbar.com/api/1/item/", data.asJsonString)
                .header("content-type", "application/json")
                .responseCode
        }
        f onSuccess {
            case res: Int => pool.shutdown()
        }
        f onFailure {
            case _  => pool.shutdown()
        }
    }
}

