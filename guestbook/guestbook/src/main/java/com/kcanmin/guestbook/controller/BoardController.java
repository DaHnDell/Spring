package com.kcanmin.guestbook.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kcanmin.guestbook.domain.dto.BoardDto;
import com.kcanmin.guestbook.domain.dto.PageRequestDTO;
import com.kcanmin.guestbook.service.BoardService;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@Log4j2
@RequestMapping("api/v1/board")
public class BoardController {
  
  @Autowired
  BoardService boardService;


  @GetMapping("list")
  public ResponseEntity<?> list(PageRequestDTO dto) {
    return ResponseEntity.ok().body(boardService.list(dto));
  }

  @DeleteMapping("{bno}")
  public ResponseEntity<?> remove(@PathVariable("bno") Long bno){
    boardService.remove(bno);
    return ResponseEntity.ok().body("success");
  }
  

  @PostMapping
  public ResponseEntity<?> register(@RequestBody BoardDto dto){
    boardService.register(dto);
    return ResponseEntity.ok().body("success");
  }

  @PutMapping("{bno}")
  public ResponseEntity<?> modify(@RequestBody BoardDto dto){
    boardService.register(dto);
    return ResponseEntity.ok().body("success");
  }
  
  @GetMapping("{bno}")
  public ResponseEntity<?> get(@PathVariable("bno") Long bno) {
    return ResponseEntity.ok().body(boardService.get(bno));
  }
  
  @GetMapping("test")
  public String test() {
    return "test";
  }
  

  

}
