package com.kcanmin.jdbc.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kcanmin.jdbc.vo.Member;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
public class MemberDaoTests {
  @Autowired
  private MemberDao dao;

  @Test
  public void testGetTime(){
    log.info(dao.getTime());
  }

  @Test
  public void testRegister(){
    Member member = Member.builder().id("1234").pw("4321").name("boot").build();
    dao.register(member);
    log.info("성공~");
  }

  @Test
  public void testOne(){
    log.info(dao.selectMember("abcd"));
  }
  
  // @Test
  // public void testList(){
  //   log.info(dao.selectList().forEach(map -> {
  //     if(map instanceof Map){
  //       log.info(((Map)map).get("id"));
  //       Object id = ((Map)map).get("id");
  //       if(id instanceof String){
  //         String idStr = (String)id;
  //       }
  //     }
  //   }
  //   ));
  // }

  @Test
  public void testList(){
    dao.selectList().forEach(log::info);
  }

  @Test
  public void testObj(){
    Object o = null;
    // 3234
    try{
      String type = (o.getClass().getSimpleName().toString());
      Long l = Long.valueOf((String)o);
      log.info(l + 2000l);
    }catch(ClassCastException cce){
      log.info("캐스트 과정의 문제");
    }catch(NumberFormatException nfe){
      log.info("문자열이지만 숫자로 구성되어 있지 않음");
    }catch(NullPointerException npe){
      log.info("null 예외");
    }

    // switch (type) {
    //   case "int":
    //     Integer.parseInt(type);
    //   case "Integer":
    //     Integer.parseInt(type);
    //   case "long":
    //     Long.valueOf(type);
    //   case "Long":
    //     Long.valueOf(type);
    //   case "Date":
    //   case "List":
    //   case "char":
    //   case "String":

    //   case "Method":
    //   case "double":
      
    //   case "null":
    //   case "Object":
    //     break;
    
    // }


  }

  @Test
  public void testUpdate(){
    Member member = dao.selectMember("1234");
    // Object[] obj = {"4321", "소똥이", "1234@gmail.com", "123-123", " 321-321"};
    member.setPw("testtesttest");
    member.setName("테스트투");
    member.setEmail("이메일 @306디테일");
    member.setRoadAddr("디지털로 306");
    member.setDetailAddr("디지털로 306디테일");
    dao.update(member);
    // log.info(dao.update(member));
    log.info(dao.selectMember("test02"));
  }

  @Test
  public void testDelete(){
    dao.delete("test01");
  }
}
