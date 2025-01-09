package com.kcanmin.guestbook.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/todo")
public class ApiController {

  @GetMapping("list")
  @CrossOrigin(origins = "http://localhost:3000")
  public List<?> todo() throws InterruptedException{
    List<?> list = IntStream.rangeClosed(1, 3).boxed().map(i->{
      Map<String, Object> map = new HashMap<>();
      map.put("id", i);
      map.put("content", "cont");
      map.put("writer", "writ");
      map.put("regDate", LocalDateTime.now());
      return map;
    }).toList();
    Thread.sleep(3000);
    return list;
  }
}
