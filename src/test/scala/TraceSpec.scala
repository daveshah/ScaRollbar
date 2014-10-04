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
}
case class TestException(message: String) extends Exception(message)
