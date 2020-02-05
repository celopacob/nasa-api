package com.preodaytest.nasaapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(NasaApiAppProperties.class)
public class NasaApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(NasaApiApplication.class, args);
	}

}
