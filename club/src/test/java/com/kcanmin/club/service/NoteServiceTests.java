package com.kcanmin.club.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kcanmin.club.entity.Attach;
import com.kcanmin.club.entity.dto.AttachDTO;
import com.kcanmin.club.entity.dto.NoteDTO;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class NoteServiceTests {
  
  @Autowired
  private NoteService service;

  @Test
  public void getTest(){
    // NoteDTO note = service.get(1L);
    // log.info(note);
  }

  @Test
  public void ListTest(){
    List<NoteDTO> note = service.list("user100@kcanmin 100.com");
    note.forEach(log::info);
  }

  @Test 
  public void modifyTest(){
    service.modify(NoteDTO.builder().num(1L).title("test-Modified-Title").content("test-Modified-Content").build());
    log.info(service.get(1L));
  }

  @Test 
  public void writeTest(){
    AttachDTO dto = AttachDTO.builder().uuid("?FGDYSDR").image(false).origin("대충 오리진").build();
    List<AttachDTO> list = new ArrayList<>();
    list.add(dto);

    log.info(service.register(
    NoteDTO
    .builder()
      .title("SpringBootTest-Register1")
      .content("SpringBootTest-Content1")
      .writerEmail("user101@kcanmin101.com")
      .attachDtos(list)
      .mno(103L)
    .build()));
  }



}
