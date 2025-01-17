package com.kcanmin.club.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kcanmin.club.entity.dto.LikesDTO;
import com.kcanmin.club.service.LikesService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.extern.log4j.Log4j2;

import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("api/v1/likes")
@Log4j2
public class LikesController {

  @Autowired
  private LikesService service;

  @GetMapping
  public boolean get(@RequestBody LikesDTO dto){
    log.info(dto);
    return service.get(dto);
  }

  @PostMapping
  public void postMethodName(@RequestBody LikesDTO dto) {
    service.toggle(dto);
  }
  


}
