package org.brbw

import akka.actor._

case class Logger(apiKey: String,environment: String) {

    def log(throwable: Throwable) : Throwable = {
        val system = ActorSystem("logging-system")
        val poster = system.actorOf(Props(new RollbarPoster),"poster")
        poster ! RollbarData(apiKey,environment,throwable)
        throwable
    }
    
}

