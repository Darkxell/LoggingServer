FROM openjdk:12-alpine

COPY target/*.jar /gametunut.jar
COPY target/gametunut.properties /gametunut.properties

EXPOSE 8000

CMD ["java", "-jar", "/gametunut.jar"]
