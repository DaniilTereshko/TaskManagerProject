FROM eclipse-temurin:19-jre-focal
MAINTAINER Daniil_Tereshko

ADD ./notification-server/target/notification-service.jar /app/

CMD ["java", "-Xmx200m", "-jar", "/app/notification-service.jar"]

EXPOSE 8080 8089