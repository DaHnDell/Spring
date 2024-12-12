package com.kcanmin.aop.ex06;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

// 어드바이스 
@Component
@Aspect
@Log4j2
public class MyAdvice {

    @Pointcut("execution(* *(..)) && args(intValue)")
    public void hello(int intValue){

    }

    @Pointcut("bean(myDependency)") // Spring이 DI 할 때 첫 글자를 소문자로 하기 때문에 Pointcut 내부의 Bean 명시 후 소문자로 호출
    public void banana(){

    }

    @Before("hello(intValue) && beanPointcut()")
    public void simpleBefore(JoinPoint joinPoint, int intValue){
        if (intValue > 1){
            log.info(joinPoint.getSignature().getName() + "심플비포입니다.");
        }
    }

    @Around("execution(* bye(..))")
    public Object simpleAround(ProceedingJoinPoint pjp) throws Throwable{
        // 다른 애들은 그냥 joinpoint 쓰는데 얘는 proceeding joinpoint를 써야 함.
        log.info("around before");
        Object O = pjp.proceed();
        log.info("around after");
        return O;
    }

    // @After
    // @After
    
    @AfterReturning("execution(* bye(..))")
    public void simpleAfter(JoinPoint joinPoint, int intValue){
            log.info(joinPoint.getSignature().getName() + "애프터리터닝은 파이널리입니다.");
    }

    // point\cut 어노테이션으로 어드바이스가 되었음. 
}
