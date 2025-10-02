
FROM archlinux:latest AS build

WORKDIR /app

RUN pacman -Syu --noconfirm && pacman -S --noconfirm openjdk-21-jdk maven

COPY pom.xml .

RUN mvn dependency:go-offline

COPY src ./src

RUN mvn clean package -DskipTests

FROM amazoncorretto:21-jre-headless

WORKDIR /app

EXPOSE 8080

COPY --from=build /app/target/*.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]