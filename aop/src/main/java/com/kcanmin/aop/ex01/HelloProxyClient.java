package com.kcanmin.aop.ex01;

import java.lang.reflect.Proxy;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class HelloProxyClient {
    public static void main(String[] args) {
        Class[] arrClasses = {HelloWorld.class}; // 배열 타입으로 만들기. 
        
        HelloWorld helloWorld = new HelloWorldImpl();
        HelloWorldHandler<HelloWorld> handler = new HelloWorldHandler<>(helloWorld);
        
        helloWorld.sayHello("DogPoo");
        log.info(helloWorld);
        log.info("+++++++++++++++++++++++++++");

        HelloWorld proxy = (HelloWorld) Proxy.newProxyInstance(HelloWorld.class.getClassLoader(), arrClasses, handler);
        // 프록시, 새 프록시 인스턴스, 인터페이스가 가지고 있는 클래스로더가 필요하고,,
        // 핸들러 

        proxy.sayHello("BirdPoo");
        log.info(proxy);
    }
}
