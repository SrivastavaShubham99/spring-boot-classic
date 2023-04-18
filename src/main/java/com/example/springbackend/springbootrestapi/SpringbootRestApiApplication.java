package com.example.springbackend.springbootrestapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Spring boot starter application",
				description = "spring boot application blog api",
				version = "v1",
				contact = @Contact(
						name = "Shubham Srivastava",
						email = "shubhamssj5.ss@gmail.com",
						url = "https://localhost:800/api/docs"
				)
		)
)
public class SpringbootRestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootRestApiApplication.class, args);
	}
}
