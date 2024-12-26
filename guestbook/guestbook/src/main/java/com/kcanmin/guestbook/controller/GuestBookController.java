package com.kcanmin.guestbook.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kcanmin.guestbook.domain.dto.PageRequestDTO;
import com.kcanmin.guestbook.domain.dto.PageResultDTO;
import com.kcanmin.guestbook.repository.GuestbookRepository;
import com.kcanmin.guestbook.service.GuestbookService;

import org.springframework.web.bind.annotation.GetMapping;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Controller
@Log4j2
@RequestMapping("guestbook")
@AllArgsConstructor
public class GuestBookController {
  // private GuestbookRepository repository;
  private GuestbookService service;

  @GetMapping({"/", "list", ""})
  public String list(Model model, PageRequestDTO dto){
    log.info("list start");
    log.info(dto);
    PageResultDTO<?,?> dto2 = service.list(dto);
    log.info(dto2);
    model.addAttribute("result", service.list(dto));
    return "/guestbook/list";
  }
  


}
