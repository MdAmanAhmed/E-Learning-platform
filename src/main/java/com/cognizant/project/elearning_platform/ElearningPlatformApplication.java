package com.cognizant.project.elearning_platform;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ElearningPlatformApplication {

	@Bean
	public ModelMapper getMapper() {
		return new ModelMapper();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(ElearningPlatformApplication.class, args);
	}

}
