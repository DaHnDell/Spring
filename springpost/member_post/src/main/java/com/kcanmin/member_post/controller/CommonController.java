package com.kcanmin.member_post.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.log4j.Log4j2;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.ResponseBody;

@Log4j2
@Controller
public class CommonController {

  @GetMapping({ "/", "/index" })
  public String index() {
    log.info("index Started");
    return "common/index";
  }

  // @GetMapping("map")
  // @ResponseBody
  // public Map<?,?> getMethodName(){
  // Map<String, Object> map = new HashMap<>();
  // map.put("A", 1);
  // return map;
  // }

  @RequestMapping(value = "msg")
  public String msg(@ModelAttribute("msg") String msg, @ModelAttribute("url") @Nullable String url, Model model)
      throws UnsupportedEncodingException {
    log.info(msg);
    log.info(url);
    if (url != null) {
      // String returnUrl = "/";
      int idx = url.indexOf("?url=") + 5;
      // returnUrl = url.substring(0, idx); // 여기가 앞에 값.
      url = url.substring(0, idx) + URLEncoder.encode(url.substring(idx), "utf-8"); // 여기가 뒤에 값. 전달해야 할 url.
      // 고정값은 있어야 하지만 변환될 수 있는 값들은 무조 건 변 수 에 넣어라.
    }
    model.addAttribute("url", url);
    return "common/msg";
  }
}
