package com.kcanmin.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class HelloController {
    @GetMapping("/")
    @ResponseBody // 너 제이슨 반환해~~
    public String index() {
        return "Hello World!";
    }
}
