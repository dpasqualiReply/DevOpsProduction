
import org.scalatest._
import sys.process._

class DockerfileSpec extends FlatSpec {

  "Java" must "be installed" in {

    var version :String = "java -version " !!

    println(version)

    assert(version.contains("java version"))
    assert(version.contains("1.8.0_151"))

  }

  "Scala" must "be installed" in {

    var sbtAbount : String = "sbt about" !!

    println(sbtAbount)

    assert(sbtAbount.contains("This is sbt 1.0.3"))
  }

}
