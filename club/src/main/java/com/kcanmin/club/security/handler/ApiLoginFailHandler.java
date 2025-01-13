package com.kcanmin.club.security.handler;

import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import net.minidev.json.JSONObject;

@Log4j2
public class ApiLoginFailHandler implements AuthenticationFailureHandler{

  @Override
  public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException exception) throws IOException, ServletException {
    log.info("LOGIN FAILED");
    log.info(exception.getMessage());

    response.setStatus(401);
    response.setContentType("application/json; charset=utf-8");
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("code", 401);
    jsonObject.put("message", exception.getMessage());

    PrintWriter out = response.getWriter();
    out.print(jsonObject);




  }

  

}
