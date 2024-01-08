package com.microservice.eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaserverApplication.class, args);
	}

	// set JAVA_HOME=C:\Program Files\Java\jdk-21.0.1

	// docker build --tag cursoms-eureka .
	// docker network create cursoms-network

	// docker run --name cursoms-eureka -p 8761:8761 --network cursoms-network cursoms-eureka

	// docker container stop cursoms-eureka

	// set JAVA_HOME=C:\Program Files\Java\amazon-corretto-17.0.9.8.1-windows-x64-jdk\jdk17.0.9_8

}
