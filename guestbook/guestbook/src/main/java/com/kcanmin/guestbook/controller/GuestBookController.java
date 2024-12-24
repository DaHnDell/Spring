package com.kcanmin.guestbook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kcanmin.guestbook.repository.GuestbookRepository;

import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.log4j.Log4j2;


@Controller
@Log4j2
@RequestMapping("guestbook")
public class GuestBookController {
  private GuestbookRepository repository;

  @GetMapping({"/", "list", ""})
  public String list(){
    log.info("list start");
    return "/guestbook/list";
  }
  


}
