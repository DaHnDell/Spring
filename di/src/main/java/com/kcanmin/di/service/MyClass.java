package com.kcanmin.di.service;

import java.util.stream.Stream;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.kcanmin.di.vo.Post;

@Configuration
public class MyClass {
    
    @Bean
    public PostService postService(){
        return () -> Stream.of(Post.builder().pno(7L).title("nonamed").build()).toList();
    }
}
