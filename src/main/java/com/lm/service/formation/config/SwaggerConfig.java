package com.lm.service.formation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;

@Configuration
@EnableSwagger2
public class SwaggerConfig {


  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
        .directModelSubstitute(LocalDateTime.class, String.class)
        .directModelSubstitute(LocalDate.class, String.class)
        .directModelSubstitute(LocalTime.class, String.class)
        .directModelSubstitute(ZonedDateTime.class, String.class)
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.lm.service.formation.controller"))
        .build();
  }

}
