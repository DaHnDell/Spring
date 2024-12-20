package com.kcanmin.todo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.kcanmin.todo.dto.TodoWriteDto;
import com.kcanmin.todo.service.TodoService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
@AllArgsConstructor
@Log4j2
public class TodoController {
  private final TodoService toDoService;
  
  @GetMapping("todos")
  public String list(Model model){
    log.info("서비스 시작");
    model.addAttribute("todos", toDoService.list());
    log.info("서비스 끝");
    return "todo-list";
  }

  @PostMapping("todos")
  public String write(TodoWriteDto dto) {
    toDoService.write(dto);
    return "redirect:/todos";
  }
  
  // @GetMapping("list")
  // public String getMethodName(@RequestParam String param) {
  //     return new String();
  // }
  
}
