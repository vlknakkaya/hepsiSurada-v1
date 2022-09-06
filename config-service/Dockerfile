FROM openjdk:11-jre-slim
RUN mkdir /app
WORKDIR /app
COPY build/libs/*.jar config-service.jar
ENTRYPOINT ["java","-jar","/app/config-service.jar"]