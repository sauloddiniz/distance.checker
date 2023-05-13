FROM maven:3.8.4-openjdk-17-slim AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn package

FROM openjdk:17-alpine3.14
COPY --from=build /app/target/distance.checker-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]

#docker build -t distance-checker . ; docker run -p 8080:8080 distance-checker "cli in Windows"
#mvn spring-boot:run "run mvn cli"