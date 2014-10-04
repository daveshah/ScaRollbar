import org.brbw.Trace
import org.scalatest.{Matchers, FlatSpec}

class TraceSpec extends FlatSpec with Matchers {

    it should "contain the exception" in {
        val testException = TestException("this is a test")

        val trace = Trace(testException)

        trace.exception.className should equal ("TestException")
    }

    it should "contain the message" in {
        val testException = TestException("this is a test")

        val trace = Trace(testException)

        trace.exception.message should equal ("this is a test")
    }

    it should "contain a list of frames" in {
        val testException = TestException("this is a test")

        val trace = Trace(testException)

        trace.frames.length should be > 1
    }

    it should "contain the mose recent frame last in the list" in {
        val testException = TestException("this is a test")

        val frame = Trace(testException).frames.last

        frame.filename should equal ("TraceSpec.scala")
        frame.lineno should equal (31)
        frame.method should not be null
    }
}
case class TestException(message: String) extends Exception(message)
