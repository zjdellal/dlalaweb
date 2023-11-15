FROM ubuntu:16.04

RUN apt-get update && \

    apt-get upgrade -y && \

    apt-get install -y  software-properties-common && \

    add-apt-repository ppa:webupd8team/java -y && \

    apt-get update && \

    echo oracle-java7-installer shared/accepted-oracle-license-v1-1 select true | /usr/bin/debconf-set-selections && \

    apt-get install -y openjdk-8-jdk && \

    apt-get clean

VOLUME /tmp
ARG JAR_FILE

COPY dlalaweb/target/dlalaweb-0.0.1-SNAPSHOT.jar dlalaweb.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/dlalaweb.jar"]
EXPOSE 8080




