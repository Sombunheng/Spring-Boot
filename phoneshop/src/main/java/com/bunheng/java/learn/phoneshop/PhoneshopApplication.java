package com.bunheng.java.learn.phoneshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(
    info = @Info(title = "Phone Shop API", version = "1.0", description = "API documentation for the Phone Shop project")
)
public class PhoneshopApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhoneshopApplication.class, args);
	}

}
