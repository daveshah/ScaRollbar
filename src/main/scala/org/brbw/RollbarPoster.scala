package org.brbw

import scalaj.http._
import akka.actor.{Actor}

class RollbarPoster extends Actor {

    private val rollbarUrl = "https://api.rollbar.com/api/1/item/"

    def receive = {
        case data:RollbarData => {
           sender ! post(data)
        }
    }

    private def post(rollBarData: RollbarData) : Unit = {
        val json = rollBarData.asJsonString
        Http.postData(rollbarUrl,json).header("content-type","appliction/json").responseCode
    }

}
