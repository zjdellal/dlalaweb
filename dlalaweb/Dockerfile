FROM tomcat:8.5.31
MAINTAINER zak-dev

WORKDIR /usr/local/tomcat

ENV TZ=US/Eastern
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

COPY target/dlalaweb-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/dlalaweb.war
RUN sed -i 's/port="8080"/port="8089"/' ${CATALINA_HOME}/conf/server.xml

CMD ["catalina.sh", "run"]


