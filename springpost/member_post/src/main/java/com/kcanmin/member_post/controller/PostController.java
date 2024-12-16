package com.kcanmin.member_post.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;

import com.kcanmin.member_post.dto.Criteria;
import com.kcanmin.member_post.dto.PageDto;
import com.kcanmin.member_post.service.PostService;
import com.kcanmin.member_post.vo.Post;

import lombok.AllArgsConstructor;


@Controller
@RequestMapping("post")
@AllArgsConstructor
public class PostController {
  private PostService service;

  @GetMapping("list")
  public void list(Criteria cri, Model model) {
    model.addAttribute("posts", service.list(cri));
		model.addAttribute("pageDto", new PageDto(cri, service.count(cri)));
  }
  
  @GetMapping("view")
  public void view(@ModelAttribute("cri") Criteria cri, Long pno, Model model) { // @ModelAttribute 어트리뷰트 옆의 괄호에다가 이름을 바인딩해줄 경우에 문제없어짐.
    model.addAttribute("post", service.view(pno));
  }

  @GetMapping("write")
  public void write(@ModelAttribute("cri") Criteria cri){}

  @PostMapping("write")
  public String postWrite(Post post, Criteria cri) {
    service.write(post);
    return "redirect:list?" + cri.getQs2();
  }
  
  
  
  


}
