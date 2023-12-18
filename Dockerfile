# Estágio 1: Estágio de construção
FROM ubuntu:latest AS build

RUN apt-get update \
    && apt-get install -y openjdk-17-jdk maven

WORKDIR /app

COPY . .

RUN mvn clean install

# Estágio 2: Estágio de execução
FROM openjdk:17-jdk-slim

EXPOSE 8080

WORKDIR /app

COPY --from=build /app/target/todolist-1.0.0.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
