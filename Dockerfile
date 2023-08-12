FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=.github/jar/mycourse-0.0.1-SNAPSHOT.jar.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]