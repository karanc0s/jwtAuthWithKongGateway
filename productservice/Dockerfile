FROM openjdk:17-jdk-slim
LABEL authors="karandeep-singh"

ADD target/products-service.jar products-service.jar
ENTRYPOINT ["java", "-jar" , "products-service.jar"]