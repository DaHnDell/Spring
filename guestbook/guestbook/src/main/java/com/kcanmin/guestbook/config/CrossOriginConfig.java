package com.kcanmin.guestbook.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CrossOriginConfig {
  @Bean
  public WebMvcConfigurer configurer(){
    return new WebMvcConfigurer() {
      @Override
      public void addCorsMappings(CorsRegistry regis){
        regis.addMapping("/**").allowedOrigins("http://localhost:3000", "http://localhost");
      }
    };
  }
}