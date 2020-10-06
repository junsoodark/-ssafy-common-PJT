package com.web.blog.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig { // http://localhost:3000/swagger-ui.html
    @Bean
    public Docket api() {
    	ParameterBuilder tokenParamBuilder = new ParameterBuilder();
    	tokenParamBuilder.name("jwt-auth-token")
				  		 .description("Access Token")
						 .modelRef(new ModelRef("string"))
						 .parameterType("header")
						 .required(false)
						 .build();

    	ParameterBuilder emailParamBuilder = new ParameterBuilder();
    	emailParamBuilder.name("user-email")
				    	 .description("user's email")
				    	 .modelRef(new ModelRef("string"))
				    	 .parameterType("header")
				    	 .required(false)
				    	 .build();
    	
    	List<Parameter> operationParameters = new ArrayList<>();
    	operationParameters.add(tokenParamBuilder.build());
    	operationParameters.add(emailParamBuilder.build());
													  
        return new Docket(DocumentationType.SWAGGER_2)
        		.globalOperationParameters(operationParameters)
        		.select()
        		.apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(Arrays.asList(apiKey()));
    }
    
    private ApiKey apiKey() {
    	return new ApiKey("Authorization", "jwt-auth-token", "header");
    }
}
