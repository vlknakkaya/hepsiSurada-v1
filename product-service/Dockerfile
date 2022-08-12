FROM openjdk:11-jre-slim
RUN mkdir /app
WORKDIR /app
COPY build/libs/*.jar product-service.jar
ENTRYPOINT ["java","-jar","/app/product-service.jar"]