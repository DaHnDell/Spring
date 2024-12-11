package com.kcanmin.aop.ex02.adv;

import org.aopalliance.intercept.MethodInvocation;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Sourcing implements org.aopalliance.intercept.MethodInterceptor{@Override
    // around 
    public Object invoke(MethodInvocation invocation) throws Throwable {
        String source = invocation.getArguments()[0].toString();
        log.info(source + "양념을 만듭니다.");
        Object o = invocation.proceed();
        log.info(source + "양념을 바릅니다.");
        return o;
    }

    // @Override
    // public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
    //     return null;
    // }
    


}
