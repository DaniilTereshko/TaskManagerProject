FROM eclipse-temurin:19-jre-focal
MAINTAINER Daniil_Tereshko

ADD ./target/eureka-naming-service.jar /app/

CMD ["java", "-Xmx200m", "-jar", "/app/eureka-naming-service.jar"]

EXPOSE 8080 8089