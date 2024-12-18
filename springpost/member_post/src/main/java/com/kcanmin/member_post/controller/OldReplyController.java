package com.kcanmin.member_post.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.bind.annotation.ResponseBody;

import com.kcanmin.member_post.service.PostService;
import com.kcanmin.member_post.vo.MyVo;
import com.kcanmin.member_post.vo.Post;

import lombok.extern.log4j.Log4j2;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestParam;


// @RestController
// @Controller
@Log4j2
// @RequestMapping("reply")
public class OldReplyController {

  // Rest 형태에선 void 잘 안쓰긴 함.
  // @ResponseBody
  // @RequestBody
  // @PathVariable

  // @ResponseBody
  @RequestMapping("test")
  public String test(){
    log.info("도착도착도착~~reply/test");
    return "hello";
  }

  // void String ResponseEntity
  // entity : 개체
  @RequestMapping("re")
  public ResponseEntity<String> re(){
    // return ResponseEntity.ok().build();
    // return ResponseEntity.notFound().build();
    // return ResponseEntity.notFound().build();
    // return ResponseEntity.status(200).build();
    return new ResponseEntity<String>("본문", HttpStatus.NOT_FOUND);
  }

  @GetMapping(value = "arr", produces =  "text/xml")
  public int[] getMethodName() {
      return new int[]{3,4,5};
  }
  
  @GetMapping("list")
  public List<String> list(){
    List<String> list = new ArrayList<>();
    list.add("가");
    list.add("나");
    list.add("다");
    list.add("차차차~");
    return list;
  }

  @GetMapping("map")
  public Map<String, Integer> map() {
    Map<String, Integer> map = new HashMap<>();
    map.put("맵맵~1", 1);
    map.put("맵맵~2", 2);
    map.put("맵맵~3", 3);
    map.put("맵맵~4", 4);
    map.put("맵맵~5", 5);
    return map;
  }

  @GetMapping("students")
  public List<?> students(){
      List<Map<?,?>> list = new ArrayList<>();
      Map<String, Object> map = new HashMap<>();
      map.put("no", 1);
      map.put("name", "넘버6");
      map.put("score", 100);
      list.add(map);
      
      map.put("no", 2);
      map.put("name", "넘버1");
      map.put("score", 1);
      list.add(map);

      return list;
  }
  
  // @GetMapping("postbuilder")
  // public Post postbuilder() {
  //   // Date date = new Calendar.DATE();
  //   Calendar c = Calendar.getInstance();
  //   Long l = 1234L;
  //   // Date cd = c.get(Calendar.DATE);
  //   // Post post = new Post(l, "null", "null널널해~", "null이 좋니?", l, null, null, l, true);
  //   // return post;
  // }

  @Autowired
  private PostService postService;
  @GetMapping("poster")
  public Post poster() {
      return postService.findBy(200L);
  }
  
  @GetMapping("mypost")
  public Post mypost(Post post) {
    // new DateTimeFormat(new Date("yyyy-MM-dd"));
    // Date date = new SimpleDateFormat("yyyy-MM-dd");
      return post;
  }
  
  @GetMapping("p1") 
  public int[] p1(@RequestParam("arr") int[] arr) {
      return arr;
  }

  @GetMapping("p2")
  public List<?> p2(@RequestParam("arr") List<?> arr) {
      return arr;
  }

  @InitBinder
  public void init(WebDataBinder wdb){
    wdb.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy_MM_dd"), false));
  }
  
  @GetMapping("myvo")
  public MyVo mv(MyVo mv) {
      return mv;
  }
  
  //%5B %5D
  
}
