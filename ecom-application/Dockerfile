FROM eclipse-temurin:25-alpine

WORKDIR /app

COPY target/ecom-application-0.0.1-SNAPSHOT.jar /app/ecom-application.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "ecom-application.jar"]
