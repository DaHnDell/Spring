package com.kcanmin.member_post.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kcanmin.member_post.vo.Attach;

import lombok.extern.log4j.Log4j2;

import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;


// @RequestMapping("")
@RestController
@Log4j2
public class FIleController {
  //업로드
  @PostMapping("upload")
  public List<Attach> upload(@RequestPart("file") List<MultipartFile> files) throws IllegalStateException, IOException{
    // files.forEach(log::error);
    // for(MultipartFile file : files){
    //   log.info(file.getOriginalFilename());
    //   log.info(file.getSize());
    //   // file.transferTo(new File("c:/upload/", file.getOriginalFilename()));
    //   file.transferTo(new File("c:/upload/", file.getOriginalFilename()));
    // }
    return files.stream().map(Attach::new).toList();    
  }
  
}
