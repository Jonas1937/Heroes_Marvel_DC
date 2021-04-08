package com.jonas.marveldcapi;

import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDynamoDBRepositories
public class MarvelDcApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarvelDcApiApplication.class, args);
		System.out.println("Heores with dataflux");
	}

}
