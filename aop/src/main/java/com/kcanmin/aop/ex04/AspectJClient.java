package com.kcanmin.aop.ex04;


import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

import com.kcanmin.aop.ex02.adv.Seasoning;

public class AspectJClient {
    public static void main(String[] args) {
        AspectJExpressionPointcut pc = new AspectJExpressionPointcut();
        pc.setExpression("execution(* two*(..))");
        // 패키지이름, 클래스이름, 반환 타입, 파라미터 모두 별표나 점점점으로 축약 가능.
        // Aspect j Expression syntax

        Advisor advisor = new DefaultPointcutAdvisor(pc, new Seasoning());

        ProxyFactory factory = new ProxyFactory(new First());
        factory.addAdvisor(advisor);
        ((First)factory.getProxy()).one();
        ((First)factory.getProxy()).two();
        ((First)factory.getProxy()).two2();
        
        factory = new ProxyFactory(new Second());
        factory.addAdvisor(advisor);
        ((Second)factory.getProxy()).one();
        ((Second)factory.getProxy()).two();
    }
}
