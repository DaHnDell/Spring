package com.kcanmin.di;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import lombok.Data;

@Data
@SpringBootApplication
public class DiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiApplication.class, args);
	}

	// @Bean 참조 자료형이라면 뭐든 등록이 가능. 그러니까 꼭 객체가 아니어도 반환이 무언가 있다면 가능. 인스턴스 자체를 반환하거나. 그래서 기본 자료형은 Bean 의 대상이 될 수 없음.
	
}
