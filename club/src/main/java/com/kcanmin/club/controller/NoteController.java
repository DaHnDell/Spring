package com.kcanmin.club.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@Log4j2
@RequestMapping("api/v1/notes")
@RequiredArgsConstructor
public class NoteController {
  
  @Autowired
  private NoteService noteService;

  @PostMapping(value = "")
  public ResponseEntity<Long> register(@RequestBody NoteDTO noteDTO){
    log.info("NoteController register start =========================");
    Long num = noteService.register(noteDTO);
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

  @GetMapping(value = "/{num}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<NoteDTO> read(@PathVariable("num") Long num) {
    return new ResponseEntity<>(noteService.get(num), HttpStatus.OK);
  }

  @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<NoteDTO>> list(String email) {
    return new ResponseEntity<>(noteService.list(email), HttpStatus.OK);
  }
  
  



}
