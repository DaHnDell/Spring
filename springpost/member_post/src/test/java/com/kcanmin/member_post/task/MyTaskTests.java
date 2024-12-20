package com.kcanmin.member_post.task;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class MyTaskTests {
  @Autowired
  private MyTask myTask;

  @Test
  public void testPrintTime(){
    myTask.printTime();
  }
}
