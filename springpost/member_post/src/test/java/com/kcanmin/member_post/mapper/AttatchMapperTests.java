package com.kcanmin.member_post.mapper;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kcanmin.member_post.vo.Attach;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class AttatchMapperTests {
  @Autowired
  private AttachMapper mapper;

  @Test
  public void testSelectAttach(){
    log.info("시작");
    String path = "2024/11/14";
    List<Attach> list = mapper.selectListByPath(path);
    list.forEach(log::info);
    log.info("=============================================================================");
    List<File> files = new ArrayList<>(Arrays.asList(new File("c:/upload", path).listFiles()));
    files.forEach(log::info);
    log.info("=============================================================================");
    List<File> list2 = list.stream().map(Attach::toFile).toList();
    list2.forEach(log::info);
  }

}
