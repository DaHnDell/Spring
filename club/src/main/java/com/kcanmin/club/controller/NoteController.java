package com.kcanmin.club.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kcanmin.club.entity.dto.NoteDTO;
import com.kcanmin.club.service.NoteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@Log4j2
@RequestMapping("api/v1/notes")
@RequiredArgsConstructor
@CrossOrigin
public class NoteController {
  

  @Autowired
  private NoteService noteService;

  @PostMapping(value = "")
  public ResponseEntity<Long> register(@RequestBody NoteDTO noteDTO){
    log.info("NoteController register start =========================");
    Long num = noteService.register(noteDTO);
    log.info(noteDTO);
    log.info("NoteController register ended =========================");
    return new ResponseEntity<>(num, HttpStatus.OK);
  }  


  
  @DeleteMapping(value = "/{num}", produces = MediaType.TEXT_PLAIN_VALUE)
  public ResponseEntity<String> remove(@PathVariable("num") Long num){
    log.info("NoteController Delete start =========================");
    noteService.remove(num);
    log.info("NoteController Delete ended =========================");
    return new ResponseEntity<>(num + "removed", HttpStatus.OK);
  }


  @PutMapping(value = "/{num}")
  public ResponseEntity<?> modify(@RequestBody NoteDTO noteDTO){
    log.info("NoteController Modify start =========================");
    log.info(noteDTO);
    noteService.modify(noteDTO);
    log.info("NoteController Modify start =========================");
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @SuppressWarnings("unchecked")
  @GetMapping(value = "/{num}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<NoteDTO> read(@PathVariable("num") Long num) {
    log.info("NoteController Get start =========================");
    // return new ResponseEntity<>(noteService.get(num), HttpStatus.OK);
    return noteService.get(num).map(ResponseEntity::ok).orElseGet(()->{
      Map<String, Object> targetNote = new HashMap<>();
      targetNote.put("code", 404);
      targetNote.put("message", "NOT_FOUND");
      ResponseEntity<?> entity = new ResponseEntity<>(targetNote, HttpStatus.NOT_FOUND);
      return (ResponseEntity<NoteDTO>)entity;
      // ResponseEntity.notFound().build();
    });
  } // map 을 할 경우 내부의 제네렉을 먹고 들어온다. 즉 map 하는 순간 NoteDTO 타입이 된다. 

  @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<NoteDTO>> list(String email) {
    return new ResponseEntity<>(noteService.list(email), HttpStatus.OK);
  }
  
  @GetMapping("listall")
  public List<NoteDTO> allList(){
    return noteService.allList();
  }


}
