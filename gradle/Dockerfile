FROM openjdk:11-jre
VOLUME /tmp
COPY *.jar /opt/app.jar
CMD java -Dsecurerandom.source=file:/dev/urandom $JAVA_OPTS -jar /opt/app.jar