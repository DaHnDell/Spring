package com.kcanmin.aop.ex04;

import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

import com.kcanmin.aop.ex02.adv.Seasoning;
import com.kcanmin.aop.ex03.adv.SimpleAdvs;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class Client {
    public static void main(String[] args) {
        ProxyFactory factory = new ProxyFactory(new First());
        factory.addAdvice(new Seasoning());
        factory = new ProxyFactory(new First());
        factory.addAdvisor(new DefaultPointcutAdvisor(new SimpleAdvs(), new Seasoning())); // matches 로 one 으로 시작하는 메서드에다가만필터를 걸어줬기 때문에,, 염지는 one 옆에다가만 하게 됨.
        ((First)factory.getProxy()).one();
        ((First)factory.getProxy()).two();
        log.info("=======================================");
    }
}
