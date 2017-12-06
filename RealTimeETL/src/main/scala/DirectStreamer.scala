import _root_.kafka.serializer._
import org.apache.spark._
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.streaming._
import org.apache.spark.streaming.kafka._

object DirectStreamer{


  val SERVER_IP : String = "devops-cloudera-vm.c.devops-data-reply.internal"
  val CLOUDERA = s"${SERVER_IP}"

  var toHive = true
  var onlyDebug = false;


  def main(args: Array[String]) {


    /**

    USAGE: DirectStreamer  topicName smallest|largest [ [-h] | [--debug | --hive] ]
    normally it push kakfa streams to hdfs folders and hive table
    --debug  just print the stream
    --hive  store only to hive datalake

    -h      show this usage

      */

    args.foreach(println)

    if(args.size < 2){

      println("""  USAGE: DirectStreamer topicName smallest|largest [ [-h] | [--debug | --hive] ]\n
                        normally it push kakfa streams to hdfs folders and hive table\n
                        --test  just print the stream\n
                        --hive  store only to hive datalake\n
                        \n
                        -h      show this usage""")
      return
    }

    if(args.size > 2)
    {
      if(args(2) == "-h")
      {
        println("""  USAGE: DirectStreamer topicName [ [-h] | [--debug | --hive] ]\n
                        normally it push kakfa streams to hdfs folders and hive table\n
                        --test  just print the stream\n
                        --hive  store only to hive datalake\n
                        \n
                        -h      show this usage""")
        return
      }

      if(args(2) == "--hive")
      {
        toHive = true
        onlyDebug = false
      }

      if(args(2) == "--debug")
      {
        toHive = false
        onlyDebug = true
      }
    }

    //DEBUG MODE
    //onlyDebug = true
    //toHive = false


    val conf = new SparkConf().setMaster("local").setAppName("Direct Streamer")
    //val sc = new SparkContext(conf)

    val ssc = new StreamingContext(conf, Seconds(10));
    ssc.checkpoint("checkpoint")

    var spark : SparkSession = null

    if(toHive)
      spark = SparkSession.builder().appName("Direct Streamer")
      .enableHiveSupport()
      .getOrCreate()
    else
      spark = SparkSession.builder().appName("Direct Streamer")
      .getOrCreate()

    //println(s"[ INFO ] ${IDGen.next}")
    println("\n\n");

    //    val topicsSet = Set(
    //      "psql-m20-tags",
    //      "psql-m20-genomescores",
    //      "psql-m20-ratings"
    //    )

    val tableName = args(0).split("-")(2)

    println(tableName)

    val topicsSet = Set(
      args(0)
    )

    val kafkaParams = Map[String, String]("bootstrap.servers" -> "localhost:9092",
      "auto.offset.reset" -> args(1),
      "group.id" -> "group1")


    val messages = KafkaUtils.createDirectStream[String, String, StringDecoder, StringDecoder](
      ssc, kafkaParams, topicsSet)

    //val getNewIDUDF = udf( () => IDGen.next)


    messages.foreachRDD(
      rdd =>
      {
        if(rdd.isEmpty){

          println("[ INFO ] Empty RDD")
        }
        else{
          val stringRDD = rdd.map(entry => entry._2)
          val jsonDF = spark.sqlContext.jsonRDD(stringRDD)

          var cols : Seq[String] = null
          var toSave : DataFrame = null

          cols = Seq("")

          tableName match {

            case "tags" => {

              cols = Seq("id", "userid", "movieid", "tag", "timestamp")
              toSave = jsonDF
                .withColumn("id", jsonDF("payload.id"))
                .withColumn("userid", jsonDF("payload.userid"))
                .withColumn("movieid", jsonDF("payload.movieid"))
                .withColumn("tag", jsonDF("payload.tag"))
                .withColumn("timestamp", jsonDF("payload.timestamp"))
                .select(cols.head, cols.tail: _*)
            }

            case "ratings" => {

              cols = Seq("id", "userid", "movieid", "rating", "timestamp")
              toSave = jsonDF
                .withColumn("id", jsonDF("payload.id"))
                .withColumn("userid", jsonDF("payload.userid"))
                .withColumn("movieid", jsonDF("payload.movieid"))
                .withColumn("rating", jsonDF("payload.rating"))
                .withColumn("timestamp", jsonDF("payload.timestamp"))
                .select(cols.head, cols.tail: _*)
            }

            case "genomescore" => {

              cols = Seq("id", "movieid", "tagid", "relevance")
              toSave = jsonDF
                .withColumn("id", jsonDF("payload.id"))
                .withColumn("movieid", jsonDF("payload.movieid"))
                .withColumn("tagid", jsonDF("payload.tagid"))
                .withColumn("relevance", jsonDF("payload.relevance"))
                .select(cols.head, cols.tail: _*)
            }

          }

          toSave.printSchema()

          if(onlyDebug)
          {
            toSave.show()
          }
          else
          {
            if(toHive)
            {
              println("SAVE TO HIVE-------------------------------------")
              toSave.write.mode("append").saveAsTable(s"datalake.${tableName}")
	      //toSave.createTempView("rawdata")
              //spark.sql(s"insert into table datalake.${tableName} select * from rawdata")
            }
          }
        }
      }
    )

    ssc.start()
    ssc.awaitTermination()

  }

/*
  def storeToHive(spark : SparkSession, table : String, dataFrame: DataFrame) = {

    //val mapped = rdd.map(raw => (IDGen.next, raw))
    //val df = spark.createDataFrame(mapped).toDF("id", "raw")
    dataFrame.registerTempTable("temptable")
    spark.sql("insert into table data_reply_db.datalake select * from temptable")

  }


  def initIDGenerator(spark : SparkSession, table : String) = {

    //spark.sql("insert into data_reply_db.datalake values (0, 'init')")
    val start = spark.sql(s"select * from ${table}").count()
    IDGen.init(start)


    try
    {
      val start = spark.sql(s"select * from ${table}").count()
      IDGen.init(start)
    }
    catch{

      case e : Exception => IDGen.init(0)

    }

  }

  object IDGen{

    var start : Long= 0;

    def init(s : Long) = {
      start = s
    }

    def next : Long = {

      val index = start
      start += 1
      index
    }

  }
*/
}
