package com.kcanmin.aop.ex05;

import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


@SpringBootApplication // 스프링부트 어플리케이션 어노테이션이란??
public class Client {
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Client.class, args);
        // First first = ctx.getBean(First.class);
        // first.one();
        // first.two();
        // first.two2();

        // // Seasoning seasoning = ctx.getBean(Seasoning.class); // dl, 디펜던시 룩업
        // ProxyFactory factory = ctx.getBean(ProxyFactory.class);
        // factory.setTarget(first);
        // factory.addAdvisor(ctx.getBean(Advisor.class));

        // First proxy = (First)factory.getProxy();
        First proxy = ctx.getBean("proxy", First.class);
        proxy.one();
        proxy.two();
        proxy.two2();


    }
}
