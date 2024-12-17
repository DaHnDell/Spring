package com.kcanmin.member_post.controller;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class CommonController {
  
  @GetMapping({"/", "/index"})
  public String index(){
    log.info("index Started");
    return "common/index";
  }

    
  @RequestMapping(value = "msg")
  public String msg(@ModelAttribute("msg") String msg, @Nullable String url) {
    log.info(msg);
    log.info(url);
    return "common/msg";
  }
}
