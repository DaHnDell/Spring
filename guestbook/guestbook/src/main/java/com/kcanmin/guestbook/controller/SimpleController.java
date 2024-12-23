package com.kcanmin.guestbook.controller;

import java.lang.ProcessBuilder.Redirect;
import java.time.LocalDateTime;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kcanmin.guestbook.domain.SimpleDto;

import lombok.extern.log4j.Log4j2;


@Log4j2
@Controller
@RequestMapping("simple")
public class SimpleController {

  @GetMapping({"ex02", "exLink"})
  public void ex02(Model model){
    model.addAttribute("list", 
    LongStream.range(1, 20)
    .mapToObj(i->SimpleDto.builder()
      .sno(i)
      .first("first"+i)
      .last("last"+i)
      .regTime(LocalDateTime.now())
    .build())); 
    // 기본 자료형이기 때문에 맵 투 오브젝트를 통해 널값을 허용해야 함?
  }
  @GetMapping("exInLine")
  public String exInLine(RedirectAttributes rttr){
    SimpleDto dto = SimpleDto.builder()
      .sno(100L)
      .first("first")
      .last("last")
      .regTime(LocalDateTime.now())
    .build();
    
    rttr.addFlashAttribute("dto",dto);
    rttr.addFlashAttribute("result", "success");
    return "redirect:ex03";
  }

  @GetMapping("ex03")
  public void ex03(){
    log.info("helllo");
  }
  
}
