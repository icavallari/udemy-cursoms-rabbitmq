package com.microservice.mscloudgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
public class MscloudgatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(MscloudgatewayApplication.class, args);
	}

	// docker build --tag cursoms-gateway .
	// docker run --name cursoms-gateway -p 8080:8080 --network cursoms-network -e EUREKA_SERVER=cursoms-eureka -e KEY_CLOAK_SERVER=cursoms-keycloak -e KEY_CLOAK_PORT=8080 cursoms-gateway

	@Bean
	public RouteLocator routes(RouteLocatorBuilder builder){
		return builder
				.routes()
					.route( r -> r.path("/clientes/**").uri("lb://cursoms-clientes"))
					.route( r -> r.path("/cartoes/**").uri("lb://cursoms-cartoes"))
					.route( r -> r.path("/avaliacoes-credito/**").uri("lb://cursoms-avaliadorcredito"))
				.build();
	}

}
