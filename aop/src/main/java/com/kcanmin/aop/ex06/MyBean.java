package com.kcanmin.aop.ex06;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MyBean {
    private MyDependency dependency;

    public void run(){
        dependency.hello(1);
        dependency.hello(2);
        dependency.hello(3);
        dependency.bye();
    }
}
