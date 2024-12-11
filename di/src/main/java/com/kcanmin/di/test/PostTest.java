// package com.kcanmin.di.test;

// import org.apache.catalina.core.ApplicationContext;
// import org.springframework.boot.SpringApplication;
// import org.springframework.boot.autoconfigure.SpringBootApplication;

// import com.kcanmin.di.service.PostService;
// import com.kcanmin.di.service.PostServiceImplNormal;

// import lombok.extern.log4j.Log4j2;

// @SpringBootApplication
// @Log4j2
// public class PostTest {
//     public static void main(String[] args) {
//         SpringApplication application = new SpringApplication(PostTest.class);
//         ApplicationContext ctx = application.run(args);
//         PostService service = ctx.getBean(PostServiceImplNormal.class);
//         service.list().forEach(log::info);
//     }
// }
