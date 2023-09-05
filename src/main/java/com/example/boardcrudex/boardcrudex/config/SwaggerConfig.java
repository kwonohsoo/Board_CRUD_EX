package com.example.boardcrudex.boardcrudex.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket board() {
        return new Docket(DocumentationType.OAS_30)
                .useDefaultResponseMessages(false)
                .groupName("게시판")
                .apiInfo(this.apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.boardcrudex"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Toy-Project")
                .description("Board CRUD EX")
                .version("1.0.0")
                .contact(new Contact("권오수", "https://github.com/kwonohsoo", "kwon524@bns.co.kr"))
                .build();
    }
}

