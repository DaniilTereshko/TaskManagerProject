FROM eclipse-temurin:19-jre-focal
MAINTAINER Daniil_Tereshko

ADD ./report-server/target/report-service.jar /app/

CMD ["java", "-Xmx200m", "-jar", "/app/report-service.jar"]

EXPOSE 8080 8089