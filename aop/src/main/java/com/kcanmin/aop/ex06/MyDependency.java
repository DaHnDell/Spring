package com.kcanmin.aop.ex06;

import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;


//target
@Log4j2
@Component
public class MyDependency {
    public void hello(int intValue){
        log.info("Hello" + intValue);
    }

    // 예전처럼 자꾸 인터페이스를 구현하지 않음. 현대적 코드일수록 더 추가됨.

    public void bye(){
        log.info("bye()");
    }
}
