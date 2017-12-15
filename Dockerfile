FROM centos:centos7.4.1708
ENV SHARE="/usr/local/share"
ENV SCALA_HOME="$SHARE/scala"
ENV SBT_HOME="$SHARE/sbt"
ENV JAVA_HOME="$SHARE/java"
ENV SPARK_HOME="$SHARE/spark"
ENV PATH="$SCALA_HOME/bin:$JAVA_HOME/bin:$SPARK_HOME/bin:$SBT_HOME/bin:$PATH"
ADD 0/jdk-8u144-linux-x64.tar.gz /
ADD 1/sbt-0.13.16.tgz /
ADD 2/scala-2.12.0-M1.tgz /
ADD 3/spark-2.2.0-bin-hadoop2.7.tgz /
RUN mv /jdk1.8.0_144 $JAVA_HOME
RUN mv /sbt $SBT_HOME
RUN mv /scala-2.12.0-M1 $SCALA_HOME
RUN mv /spark-2.2.0-bin-hadoop2.7 $SPARK_HOME
USER root
WORKDIR /root
EXPOSE 4040
EXPOSE 8080
EXPOSE 8081
CMD ["\/bin\/bash"]