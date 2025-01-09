package com.kcanmin.club.repository;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import com.kcanmin.club.entity.Member;
import com.kcanmin.club.entity.MemberRole;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class MemberRepositoryTests {
  @Autowired
  private MemberRepository repository;

  @Autowired
  private PasswordEncoder encoder;

  UserDetails userDetails;

  @Test
  public void testInsert(){
    
    IntStream.rangeClosed(1, 100).forEach(i -> {
      Member member = Member.builder().email("user"+i +"@kcanmin "+ i + ".com").name("USER" + i).password(encoder.encode("1234")).build();
      member.addMemberRole(MemberRole.USER);

      if(i > 80) {
        member.addMemberRole(MemberRole.MANAGER);
      }
      if(i > 90){
        member.addMemberRole(MemberRole.ADMIN);
      }

      repository.save(member);
    });
  }

  @Test
  @Transactional
  public void testFindByEmail() {
    Member me = repository.findByEmail("user100@kcanmin 100.com");
    log.info(me);
  }

  @Test
  @Transactional
  public void testFindByEmailAndFromSocial() {
    Member me = repository.findByEmailAndFromSocial("user100@kcanmin 100.com", false);
    log.info(me);
  }
}
