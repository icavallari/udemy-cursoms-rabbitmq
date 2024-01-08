package com.microservice.msavaliadorcredito;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableRabbit
public class MsavaliadorcreditoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsavaliadorcreditoApplication.class, args);
	}

	// docker build --tag cursoms-avaliadorcredito .

	// docker run -it --name cursoms-rabbitmq -p 5672:5672 -p 15672:15672 --network cursoms-network rabbitmq:3.9-management

	// docker run --name cursoms-avaliadorcredito --network cursoms-network -e EUREKA_SERVER=cursoms-eureka -e RABBITMQ_SERVER=cursoms-rabbitmq cursoms-avaliadorcredito

	// docker run -p 8081:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:23.0.3 start-dev
	// alterar frontend URL para http://cursoms-keycloak:8080 quando rodar com docker

	// encontrar processos na porta
	// netstat -ano | findstr :57409

	// matar processos
	// taskkill /PID 12596 /F

}
