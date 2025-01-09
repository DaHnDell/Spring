package com.kcanmin.club.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Configuration
public class SecurityConfig {
  @Bean
  public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
  };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
      http
          .csrf(csrf -> csrf.disable()) // CSRF 비활성화 (필요에 따라 활성화)
          .authorizeHttpRequests(auth -> auth
              .requestMatchers("/sample/all").permitAll()
              .requestMatchers("/sample/member").hasRole("USER")
              .requestMatchers("/sample/admin").hasRole("ADMIN") 
              .anyRequest().authenticated() // 나머지는 인증 필요
          )
          .formLogin(form -> form.permitAll()) // 기본 로그인 폼 활성화
          .logout(form -> form.logoutUrl("/member/signout"))
          .oauth2Login(Customizer.withDefaults());
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
  