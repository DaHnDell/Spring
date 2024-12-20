package com.kcanmin.member_post.mapper;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
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
  String path = "2024/12/20";

  @Test
  public void testSelectAttach(){
    log.info("시작");
    List<Attach> list = mapper.selectListByPath(path); // 정보들만 들어가 있기 때문에 전환 작업을 함.
    list.forEach(log::info);
    log.info(list.size());
    log.info("=============================================================================");
    List<File> files = new ArrayList<>(Arrays.asList(new File("c:/upload", path).listFiles()));
    files.forEach(log::info);
    log.info(files.size());
    log.info("=============================================================================");
    List<File> list2 = list.stream().map(Attach::toFile).toList(); 
    list2.forEach(log::info);
    log.info(list2.size());
    log.info("=============================================================================");
    files.removeAll(list2);
    files.forEach(log::info);
    log.info(files.size());
  }

  @Test
  public void testConvert2Attach(){ // 파일들을 어태치타입 리스트로 변환
    List<Attach> files = new ArrayList<>(Arrays.asList(new File("c:/upload", path).listFiles())
    .stream().map(Attach::fromFile).toList()); // new 연산자를 통해 새로운 리스트에 담지 않으면, 변이할 수 없다. 즉 

    files.forEach(log::info);
    log.info("=============================================================================");
    List<Attach> dbs = new ArrayList<>(mapper.selectListByPath(path));
    List<Attach> thumbs = dbs.stream().filter(Attach::isImage).peek(a->a.setUuid("t_"+a.getUuid())).toList(); // isImage가 getter?
    // dbs.stream().filter(Attach::isImage).map(a -> {a.setUuid("t_"+a.getUuid());}).toList();
    // peek = forEach랑 비슷하다. 순환은 가능하다. 
    log.info(thumbs);
    log.info(thumbs.size());

    dbs.addAll(thumbs);
    dbs.forEach(log::info);


    log.info("=============================================================================");
    files.removeAll(dbs);
    files.forEach(log::info);
    log.info(files.size());

  }


  @Test
  @DisplayName("Attach 객체의 equals() 와 hashcode() 의 재정의 확인 용도")
  public void testAttachEqualsAndHashCode(){

    Attach attach1 = Attach.builder().uuid("1.jpg").build();
    Attach attach2 = Attach.builder().uuid("1.jpg").build();
    Attach attach3 = Attach.builder().uuid("2.jpg").build();

    log.info(attach1.equals(attach2));
    log.info(attach2.equals(attach3));
    log.info(attach1.equals(attach3));

    assertTrue(attach1.equals(attach2));
    // assertTrue(attach2.equals(attach3));
    // assertTrue(attach1.equals(attach3));

    Set<Attach> attachSet = new HashSet<Attach>(Stream.of(attach1, attach2, attach3).collect(Collectors.toSet()));
    // attachSet.add(attach1);
    // attachSet.add(attach2);
    // attachSet.add(attach3);

    log.info(attachSet);

    // new Set<E>((attach1.equals(attach2)) {
      
    // };

  }

  @Test
  public void testBi(){
    Map<String, Integer> map = new HashMap<>();

    map.put("1234", 1);
    map.put("5678", 2);
    map.put("9101", 3);
    map.put("1121", 4);

    map.replaceAll((k, v)->v * v);
    log.info(map);
    map.forEach((k, v)->log.info(k + ":::" + v));
    map.forEach(log::info);

  }
}
