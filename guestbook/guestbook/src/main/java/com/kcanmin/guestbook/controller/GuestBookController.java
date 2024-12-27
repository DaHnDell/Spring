package com.kcanmin.guestbook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kcanmin.guestbook.domain.dto.GuestbookDTO;
import com.kcanmin.guestbook.domain.dto.PageRequestDTO;
import com.kcanmin.guestbook.domain.dto.PageResultDTO;
import com.kcanmin.guestbook.service.GuestbookService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;





@Controller
@Log4j2
@RequestMapping("guestbook")
@AllArgsConstructor
public class GuestBookController {
  // private GuestbookRepository repository;
  private GuestbookService service;

  @GetMapping({"/", "list", ""})
  public String list(Model model, PageRequestDTO dto){
    model.addAttribute("result", service.list(dto));
    return "/guestbook/list";
  }
  
  @GetMapping("register")
  public void register() {
  }

  @PostMapping("register")
  public String register(GuestbookDTO dto, RedirectAttributes rttr) {
    rttr.addFlashAttribute("msg", service.write(dto));
    return "redirect:list";
  }

  @GetMapping({"read", "modify"})
  public void read(Long gno, Model model, @ModelAttribute("pageDto") PageRequestDTO pageRequestDTO){
    model.addAttribute("dto", service.read(gno));
  }

  @PostMapping("modify")
  public String modify(GuestbookDTO dto, PageRequestDTO pageRequestDTO, RedirectAttributes rttr){
    service.modify(dto);
    rttr.addAttribute("page", pageRequestDTO.getPage());
    return "redirect:list";
  }

  @DeleteMapping("remove")
  public String remove(GuestbookDTO dto, PageRequestDTO pageRequestDTO, RedirectAttributes rttr) {
      service.remove(dto.getGno());
      rttr.addAttribute("page", pageRequestDTO.getPage());
      return "redirect:list";
  }
  
  
  
  


}
