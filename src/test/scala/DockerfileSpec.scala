
import org.apache.spark.rdd.RDD
import org.scalatest._

import sys.process._

class DockerfileSpec extends FlatSpec {

  "Java" must "be installed" in {

    var version = "java -version " !

    println(version)
    assert(version == 0)
  }

  "Scala" must "be installed" in {

    var sbtAbount : String = "sbt about" !!

    println(sbtAbount)
    assert(sbtAbount.contains("This is sbt"))
  }

  "showSeq" must "return [1,2,3,4,5]" in {

    val seq = SimpleJob.showSeq().collect()

    assert(seq(0) == 1)
    assert(seq(1) == 2)
    assert(seq(2) == 3)
    assert(seq(3) == 4)
    assert(seq(4) == 5)
  }

  "squaredSeq" must "return [1,4,9,16,25]" in {

    val seq = SimpleJob.squaredSeq().collect()

    assert(seq(0) == 1)
    assert(seq(1) == 4)
    assert(seq(2) == 9)
    assert(seq(3) == 16)
    assert(seq(4) == 25)
  }

}
