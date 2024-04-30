package com.retail.ecom.utility;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
@OpenAPIDefinition
public class ApplicationDocumentation {
	@Bean
	Contact contact() {
		return new Contact().name("RamKumar")
				.url("hithub.com/Ramkumar0706");
				
	}
	
	@Bean
	Info info() {
		return new Info().title("E-Commerce-Application-Flipkart Cloning")
				.description("E-Commerce-Application by using Spring Boot and React Js")
				.version("ramkumar v1").contact(contact());
	}
	@Bean
	OpenAPI openApi() {
		return new OpenAPI().info(info());
	}
}