package com.kcanmin.member_post.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@Configuration
@OpenAPIDefinition(info = @Info(title = "회원제 게시판", version = "1.0", description = "servlet / JSP migration in Spring boot"))
public class SwaggerConfig {
  
}
