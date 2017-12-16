FROM centos:centos7.4.1708
USER root
WORKDIR /opt/
RUN ["yum", "update", "-y"]
RUN ["yum", "install", "wget", "-y"]
RUN ["yum", "install", "curl", "-y"]
RUN ["yum", "install", "java-1.8.0-openjdk-1.8.0.151", "-y"]
RUN ["wget", "http://dl.bintray.com/sbt/rpm/sbt-1.0.3.rpm"]
RUN ["yum", "localinstall", "sbt-1.0.3.rpm", "-y"]
WORKDIR /root
CMD ["/bin/bash"]