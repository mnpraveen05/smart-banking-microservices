FROM openjdk:21
LABEL author="emresmart <emree.smart@gmail.com>"
WORKDIR /user-service
ADD target/user-service-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java", "-jar", "user-service-0.0.1-SNAPSHOT.jar"]