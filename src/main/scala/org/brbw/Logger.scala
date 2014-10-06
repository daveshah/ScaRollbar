package org.brbw

class Logger(apiKey: String,environment: String) {

    def log(throwable: Throwable) : Throwable = {
        RollbarPoster.post(RollbarData(apiKey,environment,throwable))
        throwable
    }
}

object Logger {
    def apply(key: String, environment: String): Logger =  new Logger(key, environment)
}
