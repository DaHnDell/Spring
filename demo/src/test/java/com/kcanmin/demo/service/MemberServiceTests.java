package com.kcanmin.demo.service;

import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
@ContextConfiguration
public class MemberServiceTests {
    @Autowired
    private MemberService memberService1;
    @Autowired
    private MemberService memberService2;
    @Autowired
    private MemberService memberService3;
    // bean 이녀석들은 모두 싱글턴이 된다,,,(기본적인 생성 전략)
    @Test
    public void testExist(){
        log.info(memberService1);
    }

    @Test
    public void testSelectNow(){
        memberService1.selectNow();
    }

    @Test
    public void testSame(){
        assertSame(memberService1, memberService2);
        assertSame(memberService2, memberService3);
        assertSame(memberService1, memberService3);
    }



}
