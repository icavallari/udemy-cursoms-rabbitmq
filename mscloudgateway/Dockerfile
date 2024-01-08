FROM maven:3.8.5-openjdk-17 as build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17-alpine
WORKDIR /app
COPY --from=build ./app/target/*.jar ./app.jar

ARG EUREKA_SERVER=cursoms-eureka
ARG KEY_CLOAK_SERVER=cursoms-keycloak
ARG KEY_CLOAK_PORT=8081

ENTRYPOINT java -jar -Dspring.profiles.active=prod app.jar