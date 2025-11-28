<<<<<<< HEAD
FROM maven:3.8.5-openjdk-17 AS builder

WORKDIR /usr/src/app

copy pom.xml .

run mvn clean package -DskipTests

FROM eclipse-temurin:17-jdk-alpine

run apk add --no-cache ca-certificates && update-ca-certificates

copy --from=builder /usr/src/app/target/biblioteca3-0.0.1-SNAPSHOT.jar /app/biblioteca3.jar
=======
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
>>>>>>> featureDou
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]