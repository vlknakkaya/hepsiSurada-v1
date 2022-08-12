FROM openjdk:11-jre-slim
RUN mkdir /app
WORKDIR /app
COPY build/libs/*.jar user-service.jar
ENTRYPOINT ["java","-jar","/app/user-service.jar"]