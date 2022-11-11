package com.app.Questionados.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.BasicAuth;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
	
	private final static String BASE_PACKAGE = "com.app.Questionados.controller";

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)//
				.select()//
				.apis(RequestHandlerSelectors.basePackage(BASE_PACKAGE))//
				.paths(PathSelectors.any()) //
				.build()
				.securityContexts(Arrays.asList(securityContext()))
                .securitySchemes(Arrays.asList(basicAuthScheme()))
				.apiInfo(getApiInfo()); //
	}

	@SuppressWarnings("deprecation")
	private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(Arrays.asList(basicAuthReference()))
                .forPaths(PathSelectors.ant("/api/**"))
                .build();
    }

    private SecurityScheme basicAuthScheme() {
        return new BasicAuth("basicAuth");
    }

    private SecurityReference basicAuthReference() {
        return new SecurityReference("basicAuth", new AuthorizationScope[0]);
    }
    
	private ApiInfo getApiInfo() {
		return new ApiInfoBuilder() //
				.title("Questionados API") //
				.version("1.0.0") //
				.description("Api de preguntas y respuestas estilo 'Preguntados'") //
				.termsOfServiceUrl("http://en.wikipedia.org/wiki/Terms_of_service") //
				.contact(new Contact("Aylen Paula Gomez", "https://github.com/AylenG", "aylen.p.gomez@gmail.com")) //
				.license("Apache License Version 2.0").licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html") //
				.build();
	}
	
}
