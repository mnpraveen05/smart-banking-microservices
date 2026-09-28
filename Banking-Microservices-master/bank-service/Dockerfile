FROM openjdk:21
LABEL author="emresmart <emree.smart@gmail.com>"
WORKDIR /bank-service
ADD target/bank-service-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java", "-jar", "bank-service-0.0.1-SNAPSHOT.jar"]