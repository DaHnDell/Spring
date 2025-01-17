package com.kcanmin.club.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kcanmin.club.entity.dto.LikesDTO;
import com.kcanmin.club.service.LikesService;

import lombok.extern.log4j.Log4j2;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("api/v1/likes")
@Log4j2
public class LikesController {

  @Autowired
  private LikesService service;

  @GetMapping
  public boolean get(LikesDTO dto){
    log.info(dto);
    return service.get(dto);
  }

  // @PreAuthorize("email == dto.email")
  @PostMapping
  public ResponseEntity<?> toggle(@RequestBody LikesDTO dto, @AuthenticationPrincipal String email) {
    // log.info(num);ssssss
    log.info(email);
    log.info(dto);
    return ResponseEntity.ok().body(Map.of("result", service.toggle(dto)));
  }
  


}
