FROM openjdk:21
LABEL author="emresmart <emree.smart@gmail.com>"
WORKDIR /invoice-service
ADD target/invoice-service-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java", "-jar", "invoice-service-0.0.1-SNAPSHOT.jar"]