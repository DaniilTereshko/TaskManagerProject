FROM eclipse-temurin:19-jre-focal
MAINTAINER Daniil_Tereshko

ADD ./task-server/target/task-service.jar /app/

CMD ["java", "-Xmx200m", "-jar", "/app/task-service.jar"]

EXPOSE 8080 8089