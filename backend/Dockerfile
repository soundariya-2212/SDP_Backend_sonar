
FROM openjdk:17-jdk-alpine


WORKDIR /app

COPY target/myapp.jar /app/template.jar

EXPOSE 8080


ENTRYPOINT ["java", "-jar", "/app/template.jar"]
