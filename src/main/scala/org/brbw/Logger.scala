package org.brbw

import java.util.concurrent.Executors

import scala.concurrent.{ExecutionContext, Future}
import scalaj.http.Http

case class Logger(apiKey: String, environment: String) {

    def log(throwable: Throwable): Throwable = {

        val pool = Executors.newCachedThreadPool()
        implicit val context = ExecutionContext.fromExecutor(pool)

        Future {
            log(RollbarData(apiKey, environment, throwable))
        }

        throwable
    }

    private def log(data: RollbarData): Int = {
        val json = data.asJsonString
        Http.postData("https://api.rollbar.com/api/1/item/", json)
            .header("content-type", "appliction/json")
            .responseCode
    }

}

