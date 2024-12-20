package com.kcanmin.todo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TodoController {
  @GetMapping("todos")
  public String list(){
    String s = "";
    return "todo-list";
  }
}
