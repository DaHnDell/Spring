package com.kcanmin.di.test;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.kcanmin.di.service.PostService;
import com.kcanmin.di.service.PostServiceImplNormal;

import lombok.extern.log4j.Log4j2;

// @SpringBootApplication
@Log4j2
public class PostTest {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(PostTest.class); // 부트 기반으로 파일 가져오기
        ConfigurableApplicationContext ctx = SpringApplication.run(PostTest.class, args); // 어쨌든 컨텍스트 기반으로 동작함. 
        PostService service = ctx.getBean(PostService.class); // required 파일 받으면 그냥 OBJ로 정의됨.    
        service.list().forEach(log::info);      
    }
}
