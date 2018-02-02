package com.udemy.microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class CurrencyExchangeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyExchangeServiceApplication.class, args);
	}
	

	
	@Bean
	/*
	 * used for spring cloud sleuth or distributed tracing.
	 * The AlwaysSampler is for all messages
	 */
	public AlwaysSampler defaultSampler(){
		return new AlwaysSampler();
	}
}
