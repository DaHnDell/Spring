package com.kcanmin.member_post.aop.advice;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.kcanmin.member_post.Exception.NotMyPostException;
import com.kcanmin.member_post.Exception.UnsignedAuthException;

@ControllerAdvice
public class MyControllerAdvice {
  @ExceptionHandler({UnsignedAuthException.class, NotMyPostException.class})
  public String unsignedAuthException(Exception exception) throws UnsupportedEncodingException{
    String msg = exception.getMessage();
    return "redirect:/msg?msg=" + URLEncoder.encode(msg, "utf-8") + "&url=/member/signin";
  }
}