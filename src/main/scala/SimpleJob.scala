import SimpleJob.{showSeq, squaredSeq}
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/*
 spark-submit --class HelloWorld --master local[*] target/scala-2.11/devopsproduction-pipelinetest_2.11-0.1.jar

 */

object HelloWorld {
  def main(args: Array[String]): Unit = {

    println("\n\n\n\nHELLO WORLD")

    if(args(0) == "-s")
      showSeq().foreach(println)
    else
      squaredSeq().foreach(println)

    println("\n\n\n\nHELLO WORLD")
  }
}

object SimpleJob {

  var s : Seq[Int] = null
  var sc : SparkContext = null

  def showSeq() : RDD[Int]= {

    if(s == null)
    {
      s = Seq(1,2,3,4,5)
      val conf = new SparkConf().setAppName("test").setMaster("local")
      sc = new SparkContext(conf)
    }

    val a = sc.parallelize(s)
    a
  }

  def squaredSeq() : RDD[Int]= {

    if(s == null)
    {
      s = Seq(1,2,3,4,5)
      val conf = new SparkConf().setAppName("test").setMaster("local")
      sc = new SparkContext(conf)
    }

    val a = sc.parallelize(s).map(x => x*x)
    a
  }

}
