FROM openjdk:8-alpine
COPY ./target/spring-micrometer-prometheus.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
