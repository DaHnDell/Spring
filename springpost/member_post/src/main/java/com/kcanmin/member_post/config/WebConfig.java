package com.kcanmin.member_post.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jakarta.servlet.ServletContextEvent;

@Configuration
public class WebConfig implements WebMvcConfigurer{

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/**").addResourceLocations("classpath:/static");
    // WebMvcConfigurer.super.addResourceHandlers(registry);
  }
  
  @Bean
  public jakarta.servlet.ServletContextListener ServletContextListener(){
    return new jakarta.servlet.ServletContextListener() {
      @Override
      public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().setAttribute("cp", sce.getServletContext().getContextPath()+"/");
      }
    };
  }
  // 웹리스너 어노테이션으로 하는 것이 아니라 빈의 라이프사이클에게 선물해주는 것.
  // 원칙에 위배되지 않는 pojo 문법으로 익명클래스를 가져다주는 형태로 하여도 Spring이 알아서 작동함.
}
