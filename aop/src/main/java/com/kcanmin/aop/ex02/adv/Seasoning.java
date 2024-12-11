package com.kcanmin.aop.ex02.adv;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.lang.Nullable;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class Seasoning implements MethodBeforeAdvice{
    // 염지 클래스

    @Override
    public void before(Method method, Object[] args, @Nullable Object target) throws Throwable {
        // TODO Auto-generated method stub
       log.info("염지를 합니다."); 
    }

    
}
