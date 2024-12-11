package com.kcanmin.aop.ex01;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class HelloWorldHandler<T> implements InvocationHandler{
    private T t;

    public HelloWorldHandler(T t){
        this.t = t;
    }

    public Object invoke(Object proxy, Method method, Object[] args)throws Throwable{
        // 위엣줄 invoke = InvocationHandler의 invoke(추상 메서드)
        long start = System.currentTimeMillis();
        Object o = method.invoke(t, args); // invoke = 간접 호출 메서드 타입의 함수
        log.info("method name :: " + method.getName());
        log.info(System.currentTimeMillis() -start + "ms"); // 시간 계산 후 로그 작성
        // return method.invoke(t, args);
        return o;

        // throw new UnsupportedOperationException
    }

}
