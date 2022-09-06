FROM openjdk:11-jre-slim
RUN mkdir /app
WORKDIR /app
COPY build/libs/*.jar gateway-service.jar
ENTRYPOINT ["java","-jar","/app/gateway-service.jar"]