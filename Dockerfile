FROM centos:centos7.4.1708
USER root
WORKDIR /opt/
RUN ["yum", "update", "-y"]
RUN ["yum", "install", "wget", "-y"]
RUN ["yum", "install", "curl", "-y"]
RUN ["yum", "install", "java-1.8.0-openjdk-1.8.0.151-1.b12.el7_4.x86_64", "-y"]
RUN ["wget", "http://dl.bintray.com/sbt/rpm/sbt-1.0.4.rpm"]
RUN ["yum", "localinstall", "sbt-1.0.4.rpm", "-y"]
WORKDIR /root
CMD ["/bin/bash"]