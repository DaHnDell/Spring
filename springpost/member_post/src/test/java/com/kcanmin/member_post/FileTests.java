package com.kcanmin.member_post;

import java.io.File;
import java.util.ArrayList;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class FileTests {
  @Test
  public void testDeleteFiles(){
    File file = new File("c:/upload/2024/12/19", "3a2f8c41-ca3a-4d77-857c-229cd22eaa27");
    file.delete();
    log.info("testEnd");
  }

  @Test
  public void testFileDirectory(){
    File file = new File("c:/upload/2024/12/19");    
    log.info(file.isDirectory());
    log.info(file.isFile());

    new ArrayList<>(Arrays.asList(file.listFiles(p -> p.getName().endsWith("png")))).forEach(log::info);
  }
}
