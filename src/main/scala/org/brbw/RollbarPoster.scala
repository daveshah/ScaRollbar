package org.brbw

import scalaj.http._

object RollbarPoster {

    private val rollbarUrl = "https://api.rollbar.com/api/1/item/"

    def post(rollBarData: RollbarData) : Unit = {
        val json = rollBarData.asJsonString
        Http.postData(rollbarUrl,json).header("content-type","appliction/json").responseCode
    }


}
