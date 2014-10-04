package org.brbw

case class Trace(t: Throwable) {
    val exception = Exception(t.getClass.getName,t.getMessage)
}

case class Exception(className: String, message: String)
