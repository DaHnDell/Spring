package com.kcanmin.di.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.log4j.Log4j2;

@SpringBootTest // 테스트 쓸 때는 무조건 이 어노테이션 쓸 것
@Log4j2
public class PostServiceTests {
    @Autowired
    private PostService service;

    @Test
    public void testExist(){
        assertNotNull(service);
    }
    
    @Test
    public void testList(){
        System.out.println("??");
        service.list().forEach(log::info);
    }

}
