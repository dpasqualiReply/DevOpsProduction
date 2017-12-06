import os
import sys

# Path for spark source folder
os.environ['SPARK_HOME']="/opt/spark-2.2.0-bin-hadoop2.7"

# Append pyspark  to Python Path
sys.path.append("/opt/spark-2.2.0-bin-hadoop2.7/python/")

try:
    from pyspark import SparkContext
    from pyspark import SparkConf
    from pyspark import SQLContext
    from pyspark.sql.types import StructType, StructField
    from pyspark.sql.types import DoubleType, IntegerType, StringType

    from configparser import ConfigParser
    import psycopg2

    print ("Successfully imported Spark Modules")

except ImportError as e:
    print ("Can not import Spark Modules", e)
    sys.exit(1)

if __name__ == '__main__':
    print "I'm working..."

    sc = SparkContext('local')
    sql = SQLContext(sc)
"""
    schema = StructType([

        StructField("UserID", IntegerType()),
        StructField("Gender", StringType()),
        StructField("Age", IntegerType()),
        StructField("Occupation", IntegerType()),
        StructField("Zip-code", IntegerType())
    ])

    data = (sql
        .read
        .format("com.databricks.spark.csv")
        .schema(schema)
        .option("header", "false")
        .option("delimiter", ';')
        .load("/home/dario/Scrivania/DevOps/ml-1m/usr2.dat"))

    print data.count()

    for d in data.take(10):
        print(d)
"""