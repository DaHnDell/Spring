package com.kcanmin.aop.Exercise.adv;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class Plating implements MethodInterceptor  {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        String menu = invocation.getArguments()[0].toString();       
        log.info(menu + " 그릇에 면을 담아요? 네.");
        Object o = invocation.proceed(); 
        log.info("주문하신 " + menu + "이(가) 나왔어요?");
        return o;
    }
    
}
