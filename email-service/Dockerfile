FROM openjdk:11-jre-slim
RUN mkdir /app
WORKDIR /app
COPY build/libs/*.jar email-service.jar
ENTRYPOINT ["java","-jar","/app/email-service.jar"]