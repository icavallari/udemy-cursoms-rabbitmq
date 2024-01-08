package com.microservice.mscartoes;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EnableRabbit
public class MscartoesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MscartoesApplication.class, args);
	}

	// docker build --tag cursoms-cartoes .

	// docker run -it --name cursoms-rabbitmq -p 5672:5672 -p 15672:15672 --network cursoms-network rabbitmq:3.9-management

	// docker run --name cursoms-cartoes --network cursoms-network -e EUREKA_SERVER=cursoms-eureka -e RABBITMQ_SERVER=cursoms-rabbitmq cursoms-cartoes

	// docker run -p 8081:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin --network cursoms-network --name cursoms-keycloak quay.io/keycloak/keycloak:23.0.3 start-dev

}
