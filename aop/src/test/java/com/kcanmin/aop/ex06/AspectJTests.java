package com.kcanmin.aop.ex06;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AspectJTests {
    @Autowired
    private MyBean myBean;

    @Test
    public void testBean(){
        myBean.run();
    }
}
