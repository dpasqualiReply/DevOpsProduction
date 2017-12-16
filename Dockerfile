FROM centos:centos7.4.1708
USER root
WORKDIR /opt/
RUN ["yum", "update", "-y"]
RUN ["yum", "install", "wget", "-y"]
RUN ["yum", "install", "curl", "-y"]
RUN ["yum", "install", "java-1.8.0-openjdk", "-y"]
RUN ["wget", "http://dl.bintray.com/sbt/rpm/sbt-0.13.12.rpm"]
RUN ["yum", "localinstall", "sbt-0.13.12.rpm", "-y"]
RUN ["yum", "install", "-y", "yum-utils", "device-mapper-persistent-data", "lvm2"]
RUN ["yum-config-manager", "--add-repo", "https://download.docker.com/linux/centos/docker-ce.repo"]
RUN ["yum", "install", "docker-ce", "-y"]
RUN ["systemctl start docker"]
RUN ["systemctl enable docker"]
WORKDIR /root
CMD ["/bin/bash"]