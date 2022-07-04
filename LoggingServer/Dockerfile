FROM openjdk:12-alpine

COPY target/*.jar /gametunut.jar
COPY target/gametunut.properties /gametunut.properties
COPY src/conf/readme.md /logs/readme.md

EXPOSE 8000

CMD ["java", "-jar", "/gametunut.jar"]
