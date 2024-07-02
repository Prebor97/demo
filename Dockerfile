FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY target/demo.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
