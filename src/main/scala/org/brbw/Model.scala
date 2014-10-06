package org.brbw
import scala.util.parsing.json._

case class RollbarData(accessToken: String,environment: String, throwable: Throwable, message: String) {

    private val data = Data(environment,
        if(message == null)  Body(Trace(throwable)) else Body(message)
    )

    def asJsonString : String = toJson.toString()

    def toJson = JSONObject(Map("access_token" -> accessToken, "data" -> data.toJson))
}

object RollbarData {
    def apply(accessToken: String,environment: String, message: String) : RollbarData = new RollbarData(accessToken,environment,null,message)
    def apply(accessToken: String,environment: String, throwable: Throwable) : RollbarData = new RollbarData(accessToken,environment,throwable,null)
}

case class Data(environment: String, body: Body) {
    def toJson = JSONObject(Map("environment" -> environment, "body" -> body.toJson))
}

case class Body(trace: Trace,message: String) {
    def toJson =  if(message == null) JSONObject(Map("trace" -> trace.toJson)) else JSONObject(Map("message" -> Map("body" -> message)))
}

object Body {
    def apply(trace: Trace) = new Body(trace,null)
    def apply(message: String) = new Body(null,message)
}

case class Trace(t: Throwable) {
    val frames = framesFrom(t)

    val exception = Exception(t.getClass.getName,t.getMessage)

    private def framesFrom(t: Throwable): List[Frame] = {
        t.getStackTrace.reverse.map( element =>
            Frame(element.getFileName,element.getLineNumber,element.getMethodName))
            .toList
    }

    def toJson = {
        JSONObject(Map("exception" -> exception.toJson,"frames" -> JSONArray(frames.map(f => f.toJson))))
    }
}

case class Exception(className: String, message: String) {
    def toJson =  JSONObject(Map("class" -> className, "message" -> message))
}

case class Frame(filename: String, lineno: Int, method: String) {
    def toJson =  JSONObject(Map("filename" -> filename , "lineno" -> lineno, "method" -> method))
}
