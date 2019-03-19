FROM adoptopenjdk/openjdk11:jdk-11.0.2.9-alpine
VOLUME /tmp
COPY *.jar /opt/app.jar
CMD java -Dsecurerandom.source=file:/dev/urandom $JAVA_OPTS -jar /opt/app.jar