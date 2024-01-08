package com.microservice.msclientes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MsclientesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsclientesApplication.class, args);
	}

	// docker build --tag cursoms-clientes .

	// docker run --name cursoms-clientes --network cursoms-network -e EUREKA_SERVER=cursoms-eureka cursoms-clientes

	// ./mvnw spring-boot:run (powershell)
	//   mvnw spring-boot:run (terminal)
}
