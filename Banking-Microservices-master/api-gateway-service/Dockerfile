FROM openjdk:21
LABEL author="emresmart <emree.smart@gmail.com>"
WORKDIR /api-gateway-service
ADD target/api-gateway-service-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java", "-jar", "api-gateway-service-0.0.1-SNAPSHOT.jar"]