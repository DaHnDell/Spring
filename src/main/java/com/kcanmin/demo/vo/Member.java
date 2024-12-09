package com.kcanmin.demo.vo;

import java.lang.System.Logger.Level;
import java.util.logging.Logger;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;


@Data
@Builder
@Slf4j
public class Member {
    private String id;
    private String pw;
    private String name;

    private static final Logger logger = Logger.getLogger("com.kcanmin.demo.vo.Member");
    private static final java.lang.System.Logger logger2 = System.getLogger("com.kcanmin.demo.vo.Member");//java.lang.System.Logger; 


    public static void main(String[] args) {
        Member member = Member.builder().id("abcd").pw("1234").name("BirdPoo").build();
        System.out.println(member);    
        logger.info(member.toString());
        logger2.log(Level.INFO, member);

        log.info(member.toString()); // Slf4j
    }

}
