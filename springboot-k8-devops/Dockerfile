FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/springboot-k8-devops-1.0.jar app.jar
EXPOSE 8080/tcp
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","app.jar"]
