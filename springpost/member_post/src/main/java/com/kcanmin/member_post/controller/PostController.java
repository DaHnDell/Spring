package com.kcanmin.member_post.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.kcanmin.member_post.aop.annotation.MyPost;
import com.kcanmin.member_post.aop.annotation.SigninCheck;
import com.kcanmin.member_post.dto.Criteria;
import com.kcanmin.member_post.dto.PageDto;
import com.kcanmin.member_post.service.PostService;
import com.kcanmin.member_post.vo.Member;
import com.kcanmin.member_post.vo.Post;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping("post")
@AllArgsConstructor
public class PostController {
  private PostService service;
  private HttpSession session;
  // private MemberService memberService;

  @GetMapping("list")
  public String list(Criteria cri, Model model) {
    model.addAttribute("posts", service.list(cri));
    model.addAttribute("pageDto", new PageDto(cri, service.count(cri)));
    return "post/list";
    // 원래는 접두사가 포함되어 있음. forward / redirect 둘 중 하나. 기본값이 forward이기 때문에 명시하지 않으면
    // forward가 됨.
  }

  @GetMapping("view")
  public void view(@ModelAttribute("cri") Criteria cri, Long pno, Model model) { // @ModelAttribute 어트리뷰트 옆의 괄호에다가 이름을
                                                                                 // 바인딩해줄 경우에 문제없어짐.
    model.addAttribute("post", service.view(pno));
  }

  @GetMapping("write")
  @SigninCheck
  public void write(@ModelAttribute("cri") Criteria cri) {
  }

  @PostMapping("write")
  public String postWrite(Post post, Criteria cri) {
    // log.error(post);
    // service.write(post);
    // return "redirect:list?" + cri.getQs2();
    // post.setCno(cri.getCategory());
    int crint = cri.getCategory();
    Long lcrint = Long.valueOf(crint);
    post.setCno(lcrint);
    log.info(post);
    service.write(post);
    return "redirect:list?" + cri.getQs2();
  }

  @GetMapping("modify")
  @SigninCheck
  // @MyPost(value = "writer")
  public void modify(@RequestParam("pno") Long pno, Model model, Criteria cri,
      @SessionAttribute(name = "member") Member member, String writer) {
    // String writer = service.findBy(pno).getWriter();
    // String sessionID = model.getAttribute("id");
    log.info(pno);
    log.info(cri);
    log.info(member);
    Post post = service.findBy(pno);
    if (member == null || !member.getId().equals(post.getWriter())) {
      throw new RuntimeException("Invalid Approach");
    }
    model.addAttribute("post", service.findBy(pno));
  }

  // @MyPost(value = "writer")
  @PostMapping("modify")
  @SigninCheck
  @MyPost
  public String postModify(Post post, Criteria cri) {
    // 실제로 본인 게시글이 아니면 예외를 던져서 컨트롤러 어드바이스가 낚아채도록 설계해야만 함.
    // String sessionLogin = session.getAttribute("member")
    // if( == post.getWriter()){
    // }
    // log.info(post);
    // log.info(cri);
    service.modify(post);
    return "redirect:list?" + cri.getQs();
  }

  @RequestMapping("remove")
  @SigninCheck
  public String remove(@RequestParam("pno") Long pno, Criteria cri) {
    service.remove(pno);
    return "redirect:list?" + cri.getQs();
  }

}
