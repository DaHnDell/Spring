package com.kcanmin.club.security.filter;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.kcanmin.club.util.JWTUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import net.minidev.json.JSONObject;

@Log4j2
@Component
public class ApiCheckFilter extends OncePerRequestFilter{
  private AntPathMatcher antPathMatcher;
  private JWTUtil jwtUtil;
  private String pattern;

  public ApiCheckFilter(String pattern, JWTUtil jwtUtil) {
    this.antPathMatcher = new AntPathMatcher();
    this.pattern = pattern;
    this.jwtUtil = jwtUtil;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
  throws ServletException, IOException {
    if(antPathMatcher.match(pattern, request.getRequestURI())) {
      log.info("API CHECK FILTER::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
      log.info(request.getRequestURI());
    
      if(checkAuthHeader(request)){
        String token = request.getHeader("Authorization").substring(7);
        String email = jwtUtil.validateExtract(token);

        Authentication authentication = new UsernamePasswordAuthenticationToken(email, null, null);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
        return;
      }
      else{
        response.setContentType("application/json; charset=utf-8");;
        response.setStatus(403);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 403);
        jsonObject.put("message", " :: FAILED TO CHEK API TOKEN :: ");
        response.getWriter().print(jsonObject);
      }
      return;
    }
    filterChain.doFilter(request, response);
  }
  
  private boolean checkAuthHeader(HttpServletRequest request){
    boolean result = false;
    String authHeader = request.getHeader("Authorization");

    if(StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")){
      log.info("Auth Test : exist :::" + authHeader);
      try{
        String email = jwtUtil.validateExtract(authHeader.substring(7));
        log.info("validate Result : " + email);
        result = email.length() > 0;
      }catch(Exception e){
        e.printStackTrace();
      }
      return result;
      // if(authHeader.equals("12345678")){ // 요청 시마다 헤더 체크를 함.
      //   result = true;
      // }
    }
    return result;
  }

}
