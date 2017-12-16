FROM centos:centos7.4.1708
USER root
WORKDIR /opt/
RUN ["yum", "update", "-y"]
RUN ["yum", "install", "wget", "-y"]
RUN ["yum", "install", "curl", "-y"]
RUN ["yum", "install", "java-1.8.0-openjdk", "-y"]
RUN ["yum", "install", "bc", "-y"]
RUN ["wget", "https://dl.bintray.com/sbt/rpm/sbt-1.0.0.rpm"]
RUN ["yum", "install", "sbt-1.0.0.rpm", "-y"]
RUN ["rm", "-rf", "/root/.sbt"]
WORKDIR /root
CMD ["/bin/bash"]