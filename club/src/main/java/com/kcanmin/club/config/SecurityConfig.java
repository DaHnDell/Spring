package com.kcanmin.club.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
      http
          .csrf().disable() // CSRF 비활성화 (필요에 따라 활성화)
          .authorizeHttpRequests(auth -> auth
              // .requestMatchers("//").permitAll() // `/public/` 경로는 인증 없이 접근 가능
              .anyRequest().authenticated() // 나머지는 인증 필요
          )
          .formLogin(); // 기본 로그인 폼 활성화
      return http.build();
  }
}
// @Configuration
// public class SecurityConfig{
    // @Bean
    // protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
    //     http
    //         .csrf(AbstractHttpConfigurer::disable)
    //         .headers((headers) -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
    //         .authorizeHttpRequests(auth -> auth
    //         .requestMatchers(
    //                 new AntPathRequestMatcher("/"),
    //                 new AntPathRequestMatcher("/css/**"),
    //                 new AntPathRequestMatcher("/images/**"),
    //                 new AntPathRequestMatcher("/js/**"),
    //                 new AntPathRequestMatcher("/h2-console/**"),
    //                 new AntPathRequestMatcher("/profile")
    //         ).permitAll());
    //     return http.build();
    // }

    
  // }
  