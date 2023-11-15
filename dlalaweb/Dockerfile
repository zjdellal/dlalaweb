FROM openjdk:8-jdk-alpine


VOLUME /tmp

ADD target/dlalaweb-0.0.1-SNAPSHOT.war app.war

RUN sh -c 'touch /app.war'

CMD java -Dspring.couchbase.bootstrap-hosts=$HOSTS -Djava.security.egd=file:/dev/./urandom -jar /app.war



