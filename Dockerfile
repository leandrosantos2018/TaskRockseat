FROM  ubuntu:latest As Build

RUN apt-get update 
RUN apt-get install openJDK-17-jdk -y

COPY . .

RUN apt-get install mavem -y
RUN mvn clean install

EXPOSE 8080

COPY --from=build /target/todolist-1.0.0.jar app.jar

ENTRYPOINT [ "java ","-jar", "app.jar" ]