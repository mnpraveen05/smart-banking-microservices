FROM openjdk:21
LABEL author="emresmart <emree.smart@gmail.com>"
WORKDIR /discovery-client-service
ADD target/discovery-client-service-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java", "-jar", "discovery-client-service-0.0.1-SNAPSHOT.jar"]