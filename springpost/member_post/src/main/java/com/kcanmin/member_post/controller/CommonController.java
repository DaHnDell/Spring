package com.kcanmin.member_post.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class CommonController {
  
  @GetMapping({"/", "/index"})
  public String index(){
    log.info("index Started");
    return "common/index";
  }
}
