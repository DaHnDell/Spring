package com.kcanmin.member_post.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jakarta.servlet.ServletContextEvent;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/**").addResourceLocations("classpath:/static");
    // WebMvcConfigurer.super.addResourceHandlers(registry);
  }

  @Bean
  public jakarta.servlet.ServletContextListener ServletContextListener() {
    return new jakarta.servlet.ServletContextListener() {
      @Override
      public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().setAttribute("cp", sce.getServletContext().getContextPath() + "/");
      }
    };
  }
  // 웹리스너 어노테이션으로 하는 것이 아니라 빈의 라이프사이클에게 선물해주는 것.
  // 원칙에 위배되지 않는 pojo 문법으로 익명클래스를 가져다주는 형태로 하여도 Spring이 알아서 작동함.
  // contextpath 리스너를 살리는 형태로도 가능하긴 하지만, 이에 따른 추가적인 작업이 더욱 더 복잡함.
  // 또한 모듈화, 응집성 측면에서도 webconfig 에 정의해주는 것이 더 타당하다.

  // @Bean
  // public CharsetEncoder charsetEncoder(){
  // return new CharsetEncoder() {
  // @Override
  // public void charset(){
  // \
  // }
  // };
  // }

}
