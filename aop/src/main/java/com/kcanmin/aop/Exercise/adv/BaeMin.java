package com.kcanmin.aop.Exercise.adv;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class BaeMin implements AfterReturningAdvice{

    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        log.info("배달기사를 호출해요?");
    }
    
}
