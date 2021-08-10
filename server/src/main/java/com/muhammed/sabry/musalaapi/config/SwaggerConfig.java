package com.muhammed.sabry.musalaapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Bean
	public Docket swaggerApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.forCodeGeneration(true)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build()
				.apiInfo(metaData());
	}
	
	private ApiInfo metaData() {
		
		return new ApiInfo(
				"Musala Soft REST API",
				"API description",
				"1.0.0",
				null,
				new Contact("Muhammed",
						"",
						"muhammed.sabry.refai@gmail.com"),
				"Copyright (c) @MuhammedSabry All Rights Reserved ",
				null,
				new ArrayList<>());
	}
}
