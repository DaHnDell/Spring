package com.kcanmin.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.kcanmin.demo.service.MemberService;

@Controller
@RequestMapping("member")
public class MemberController {
    @Autowired
    private MemberService service;

    @RequestMapping("")
    public String index(Model model){
        model.addAttribute("now", service.selectNow());
        return "hello";
    }
}
