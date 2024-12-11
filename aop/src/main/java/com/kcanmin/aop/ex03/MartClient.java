package com.kcanmin.aop.ex03;

import org.springframework.aop.Pointcut;
import org.springframework.aop.PointcutAdvisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor;

import com.kcanmin.aop.ex02.adv.Packaging;
import com.kcanmin.aop.ex03.adv.ThrowLog;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class MartClient {
    public static void main(String[] args) {
        Mart mart = new MartImpl();

        ProxyFactory factory = new ProxyFactory();
        factory.setTarget(mart);
        // factory.setTargetClass(Mart.class); // targetClass로 지정할 경우 클래스 이름으로 직접접근 할 수 있다.
        factory.addAdvice(new Packaging());
        factory.addAdvice(new ThrowLog());

        Mart mart2 = (Mart)factory.getProxy();
        try{
            mart2.getProduct("후추");
        }catch(RuntimeException e){
            log.error( e.getMessage() + "캐치! 네, 공 잘 받았어요. 근데 포장은 못해요!");
            e.printStackTrace();
        }

        // PointcutAdvisor advisor = new StaticMethodMatcherPointcutAdvisorExtension();
    }
}
    // 1. ProxyFactory 생성

    // 2. target을 MartImpl로 지정

    // 3. ex2의 packaging 을 advice로 지정

    // 4. ex3의 ThrowLog를 advice로 지정

    // 5. proxy 객체 생성 후 getName 호출