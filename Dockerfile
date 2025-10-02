
FROM ubuntu:latest AS build

WORKDIR /app

RUN apt-get update
RUN apt-get install openjdk-21-jdk -y

COPY pom.xml .

RUN apt-get install maven -y
RUN mvn dependency:go-offline

COPY src ./src

RUN mvn clean package -DskipTests

FROM openjdk:21-jdk-slim

WORKDIR /app

EXPOSE 8080

COPY --from=build /app/target/*.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]