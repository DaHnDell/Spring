package com.kcanmin.member_post.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@Log4j2
public class CommonController {
  
  @GetMapping({"/", "/index"})
  public String index(){
    log.info("index Started");
    return "common/index";
  }

  @GetMapping("map")
  @ResponseBody
  public Map<?,?> getMethodName(){
    Map<String, Object> map = new HashMap<>();
    map.put("A", 1);
    return map;
  }
    
  @RequestMapping(value = "msg")
  public String msg(@ModelAttribute("msg") String msg, @Nullable String url) {
    log.info(msg);
    log.info(url);
    return "common/msg";
  }
}
