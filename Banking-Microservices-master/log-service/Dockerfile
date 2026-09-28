FROM openjdk:21
LABEL author="emresmart <emree.smart@gmail.com>"
WORKDIR /log-service
ADD target/log-service-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java", "-jar", "log-service-0.0.1-SNAPSHOT.jar"]