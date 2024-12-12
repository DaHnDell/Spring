package com.kcanmin.aop.Exercise;

import org.springframework.aop.framework.ProxyFactory;

import com.kcanmin.aop.Exercise.adv.BaeMin;
import com.kcanmin.aop.Exercise.adv.Plating;
import com.kcanmin.aop.Exercise.adv.makeNoodle;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class JKProxyClient {
    public static void main(String[] args) {
        JJM jjm = new JJM();
        JP jp = new JP();

        ProxyFactory factory = new ProxyFactory();
        factory.setTarget(jp);
        factory.addAdvice(new makeNoodle());
        factory.addAdvice(new Plating());
        factory.addAdvice(new BaeMin());
        
        JP jp2 = (JP)factory.getProxy();
        
        jp2.chooseSoup("짬뽕");
        
        log.info("=========================================");
        
        factory = new ProxyFactory(jjm); // 이렇게?
        // factory.setTarget(jjm); 바로 넣을 수도 있어요.
        factory.addAdvice(new makeNoodle());
        factory.addAdvice(new Plating());
        // factory.addAdvice(new BaeMin());

        JJM jjm2 = (JJM)factory.getProxy();
        jjm2.chooseSoup("짜장");
    }
}
