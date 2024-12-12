package com.kcanmin.aop.ex05;

import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Component("target")
public class First{
    public void one(){
        log.info("First.one()");
    }
    // 메서드 앞에 bean을 붙이면 반환하는 애들이 빈이 됨.
    public void two(){
        log.info("First.two()");
    }
    public void two2(){
        log.info("First.two2()");
    }
}
