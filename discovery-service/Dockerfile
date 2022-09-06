FROM openjdk:11-jre-slim
RUN mkdir /app
WORKDIR /app
COPY build/libs/*.jar discovery-service.jar
ENTRYPOINT ["java","-jar","/app/discovery-service.jar"]