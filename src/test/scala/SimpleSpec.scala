import org.scalatest.FlatSpec

class SimpleSpec extends FlatSpec {

     "A SimpleSpec" should "compile just fine" in {
          assert(true)
     }

     "The HelloWorlder" should "say hello!" in {
          var helloWorlder = new HelloWorlder()
          assert("hello!" == helloWorlder.sayHello())
     }
}
