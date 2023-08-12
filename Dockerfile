FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
ADD target/mycourse-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]