package com.kcanmin.club.security.filter;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import com.kcanmin.club.security.handler.ApiLoginFailHandler;
import com.kcanmin.club.util.JWTUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class ApiLoginFilter extends AbstractAuthenticationProcessingFilter{

  private JWTUtil jwtUtil;

  public JWTUtil jwtUtil(){
    return new JWTUtil();
  };

  public ApiLoginFilter(String defaultFilterProcessUrl){
    super(defaultFilterProcessUrl);
  }

  public ApiLoginFilter(String defaultFilterProcessUrl, JWTUtil jwtUtil){
    super(defaultFilterProcessUrl);
    this.jwtUtil = jwtUtil;
  }
  

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
      throws AuthenticationException, IOException, ServletException {
    log.info("API LOGIN FILTER . ATTEMPTAUTHENTICATION :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
    String email = request.getParameter("email");
    String pw = "1234";

    log.info("*****************email : " + email);
    log.info("********************pw : " + pw);

    // if(email == null){
    //   throw new BadCredentialsException("email is null");
    // }
    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, pw);
    Authentication authentication = getAuthenticationManager().authenticate(authenticationToken);
    // return getAuthenticationManager().authenticate(authenticationToken);
    log.info(authentication.getPrincipal());
    return authentication;
  }
  
  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
  Authentication authResult) throws IOException, ServletException {
    // log.info(authResult.getPrincipal());
    log.info(authResult.getName());
    String email = authResult.getName();
    log.info(authResult);
    SecurityContextHolder.getContext().setAuthentication(authResult);

    try {
      String token = jwtUtil.generateToken(email);
      response.setContentType("text/plain");
      response.getOutputStream().write(token.getBytes());
      log.info("==============================");
    } catch (Exception e) {
      e.printStackTrace();
    }
    // super.successfulAuthentication(request, response, chain, authResult);
  }

  // @Bean
  // public ApiLoginFilter apiLoginFilter() throws Exception{
  //   ApiLoginFilter apiLoginFilter = new ApiLoginFilter("api/login", jwtUtil());
  //   apiLoginFilter.setAuthenticationManager(authenticationMa);

  //   apiLoginFilter.setAuthenticationFailureHandler(new ApiLoginFailHandler());
  //   return apiLoginFilter;
  // }

}
