package com.kcanmin.aop.ex03.adv;

import java.lang.reflect.Method;

import org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor;

public class SimpleAdvs extends StaticMethodMatcherPointcutAdvisor {
    // 중간 지점의 추상 클래스.
    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        
        return method.getName().startsWith("one");
    }
    
    
}
