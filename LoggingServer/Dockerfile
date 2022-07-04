FROM openjdk:12-alpine

COPY target/*.jar /gametunut.jar
COPY target/gametunut.properties /gametunut.properties
RUN mkdir -p /logs
COPY src/conf/readme.md /logs/readme.md

VOLUME ["/logs"]

EXPOSE 8000

CMD ["java", "-jar", "/gametunut.jar"]
