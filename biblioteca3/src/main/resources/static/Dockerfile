FROM maven:3.8.5-openjdk-17 AS builder

WORKDIR /usr/src/app

copy pom.xml .

run mvn clean package -DskipTests

FROM eclipse-temurin:17-jdk-alpine

run apk add --no-cache ca-certificates && update-ca-certificates

copy --from=builder /usr/src/app/target/biblioteca3-0.0.1-SNAPSHOT.jar /app/biblioteca3.jar
EXPOSE 8080

ETRYPOINT ["java","-jar","/app/biblioteca3.jar"]