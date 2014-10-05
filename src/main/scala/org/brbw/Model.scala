package org.brbw

case class RollbarData(accessToken: String, data: Data)

case class Data(environment: String, body: Body)

case class Body(trace: Trace)

case class Trace(t: Throwable) {
    val frames = framesFrom(t)

    val exception = Exception(t.getClass.getName,t.getMessage)

    private def framesFrom(t: Throwable): List[Frame] = {
        t.getStackTrace.reverse.map( element =>
            Frame(element.getFileName,element.getLineNumber,element.getMethodName))
            .toList
    }
}

case class Exception(className: String, message: String)

case class Frame(filename: String, lineno: Int, method: String)
