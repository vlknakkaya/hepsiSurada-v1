FROM openjdk:11-jre-slim
RUN mkdir /app
WORKDIR /app
COPY build/libs/*.jar order-service.jar
ENTRYPOINT ["java","-jar","/app/order-service.jar"]