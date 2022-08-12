FROM openjdk:11-jre-slim
RUN mkdir /app
WORKDIR /app
COPY build/libs/*.jar stock-service.jar
ENTRYPOINT ["java","-jar","/app/stock-service.jar"]