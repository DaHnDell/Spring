package com.kcanmin.guestbook.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import lombok.extern.log4j.Log4j2;


// Autoconfiguration이 잡혀있지 않아서 그렇다..
// @WebMvcTest(BoardController.class)
@Log4j2
@SpringBootTest
@AutoConfigureMockMvc
public class BoardControllerTests {
  
  @Autowired
  private MockMvc mockMvc;
  
  // @BeforeEach
  // public void init(WebApplicationContext ctx){
  //   mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
  //   // SpringbootTest로만 하려면 mockMVC 빌더를 사용해야 한다..
  // }

  @Test
  public void testTest() throws Exception{
    mockMvc.perform(get("/api/v1/board/test")).andExpect(status().isOk()).andExpect(content().string("test"));
  }


  @Test
  public void testList() throws Exception{
    mockMvc.perform(get("/api/v1/board/list")
    .param("page", "1")
    .param("size", "5")
    .param("type", "TC")
    .param("keyword", "8"))
    .andExpect(status().isOk())
    .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
  }


  @Test
  public void testRegister() throws Exception{
    String jsonStr = "{\r\n" + //
    "    \"title\" : \"Controller test hello\",\r\n" + //
    "    \"content\":\"hello\",\r\n" + //
    "    \"memberEmail\":\"null4@com\"\r\n" + //
    "}";
    mockMvc.perform(post("/api/v1/board")
    .content(jsonStr).contentType(MediaType.APPLICATION_JSON_VALUE))
    .andDo(print());
  }


}
