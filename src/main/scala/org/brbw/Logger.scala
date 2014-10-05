package org.brbw

class Logger(apiKey: String,environemt: String) {

    def log(message: String): Unit = {
        

    }


}

object Logger {
    def apply(key: String, environment: String): Logger =  new Logger(key, environment)
}
