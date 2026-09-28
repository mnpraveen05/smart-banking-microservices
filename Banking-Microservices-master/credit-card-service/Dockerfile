FROM openjdk:21
LABEL author="emresmart <emree.smart@gmail.com>"
WORKDIR /credit-card-service
ADD target/credit-card-service-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java", "-jar", "credit-card-service-0.0.1-SNAPSHOT.jar"]