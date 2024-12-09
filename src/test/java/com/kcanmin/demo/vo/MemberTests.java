package com.kcanmin.demo.vo;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
// import lombok.extern.log4j.Log4j2;

@SpringBootTest
// @Log4j2
public class MemberTests {
    // 여러가지 테스트 메서드들이 들어갈 예정
    // private Member member = Member.builder().build();

    // 테스트 메서드의 작명규칙은 test + 테스트할 객체 혹은 인스턴스(혹은 그 대상)
    @Test
    public void testMember(){
        //테스트 수행 후 초록색 체크박스를 확인해야 함. 이게 문제가 없다는 것.

        //given
        Member m1 = new Member("abcd", "1234", "BirdPoo");
        Member m2 = new Member("abcd", "1234", "BirdPoo");
        // Member m1 = Member.builder().id("abcd").pw("1234").name("BirdPoo").build();
        // Member m2 = Member.builder().id("abcd").pw("1234").name("BirdPoo").build();
        //when~then

        //expect / assert
        // assertNull(member.getId()); // member가 null 인가? 를 테스트.
        // log.info(String.format("%s@X", m1.getClass().getName(), m1.hashCode()));
        // log.info(String.format("%s@X", m2.getClass().getName(), m2.hashCode()));
        assertEquals(m1, m2); // 같은 값을 가질 경우 테스트 통과
        System.out.println(m1.equals(m2)); 
        assertSame(m1, m2); // 같은 주소값을 가질 경우 테스트 통과
    }

    // 테스트를 하게 될 경우 @SpringBootApplication 의 클래스가 무조건 있어야 함.

    @Autowired
    @Qualifier("member")
    private Member member;

    @Test
    public void testDI(){
        System.out.println(member);
    }
}
