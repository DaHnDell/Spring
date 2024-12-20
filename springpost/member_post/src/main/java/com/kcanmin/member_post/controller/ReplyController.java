package com.kcanmin.member_post.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.kcanmin.member_post.dto.ReplyCri;
import com.kcanmin.member_post.service.ReplyService;
import com.kcanmin.member_post.vo.Member;
import com.kcanmin.member_post.vo.Reply;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;



@RestController
@RequestMapping("reply")
@AllArgsConstructor
@Log4j2
public class ReplyController {
  private ReplyService service;
  // 목록조회
  // 단일조회
  // 등록
  // 수정
  // 삭제

  @PostMapping
  @Operation(summary = "reply write", description = "댓글 작성을 위해 content, writer, pno 를 받고 return Json", 
  responses = {
                // @ApiResponse(responseCode = "200", description = "작성 성공", content = @Content(schema = @Schema(implementation = String.class))),
                @ApiResponse(responseCode = "200", description = "작성 성공")
              }
            )
  public ResponseEntity<?> write(@RequestBody Reply reply) {
    log.info(reply);
    // service.write(reply);
    // return ResponseEntity.ok().body("success");
    return service.write(reply) > 0 ? ResponseEntity.ok().body("success") : ResponseEntity.internalServerError().build();
  }

  @GetMapping({"list/{pno}", "list/{pno}/{lastRno}", "list/{pno}/{lastRno}/{amount}"})
  public Map <?, ?> list(@SessionAttribute(required = false) Member member, @PathVariable Long pno, ReplyCri cri, @PathVariable(required = false, name="lastRno") Optional<Long> lastRno, Optional<Integer> amount){
    // cri.setAmount(amount.orElse(10)); // optional -> 에서 만약 값이 없을 경우에 
    cri.setAmount(amount.orElseGet(()->10)); // 
    cri.setLastRno(lastRno.orElse(0L));  
    log.info(cri);
    return service.list(pno, cri, member);
  }
  

  @Operation(summary = "reply single select", description = "@PathVariable인 rno 값을 입력 받고 해당 댓글을 json으로 리턴")
  @GetMapping("{rno}")
  public ResponseEntity<?> view(@PathVariable Long rno) {
    service.findBy(rno);
    // log.info(service.findBy(rno));
    return ResponseEntity.ok().body(service.findBy(rno));
  }

  @PutMapping
  public ResponseEntity<?> modify(@RequestBody Reply reply) {
    log.info("댓글? " + reply);
    service.modify(reply);
    return ResponseEntity.ok().body("success");
  }

  // @RequestMapping("{rno}") // 내일 물어보셈 // 하면 안됨,, Rest와 일반 controller의 차이.
  @DeleteMapping("{rno}")
  public ResponseEntity<?> postRemove(@PathVariable Long rno){
    service.remove(rno);
    return ResponseEntity.ok().body("success");
  }
}