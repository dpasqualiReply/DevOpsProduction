FROM centos:centos7.4.1708
USER root
WORKDIR /opt/
RUN ["yum", "update", "-y"]
RUN ["yum", "install", "wget", "-y"]
RUN ["yum", "install", "curl", "-y"]
RUN ["yum", "install", "java-1.8.0-openjdk", "-y"]
RUN ["wget", "http://dl.bintray.com/sbt/rpm/sbt-0.13.12.rpm"]
RUN ["yum", "install", "sbt-0.13.12.rpm", "-y"]
RUN ["yum", "install", "-y", "yum-utils", "device-mapper-persistent-data", "lvm2"]
WORKDIR /root
CMD ["/bin/bash"]
