package org.brbw

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
