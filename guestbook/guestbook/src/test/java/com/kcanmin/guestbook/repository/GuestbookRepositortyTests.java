package com.kcanmin.guestbook.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.transaction.annotation.Transactional;

import com.kcanmin.guestbook.domain.entity.GuestbookEntitiy;
import com.kcanmin.guestbook.domain.entity.QGuestbookEntitiy;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class GuestbookRepositortyTests {
  @Autowired
  private GuestbookRepository repository;

  @Transactional
  @Test
  public void testExists(){
    log.info(repository);
  }

  @Transactional
  @Test
  public void testQuerydsl(){
    GuestbookEntitiy.GuestbookEntitiyBuilder guestbookbuilder = GuestbookEntitiy.builder();
    guestbookbuilder.content("콘텐트입니다."); // return this 의 결과물.
    guestbookbuilder.title("타이틀입니다.");
    GuestbookEntitiy entitiy = guestbookbuilder.build();
    // 내부 빌더를 호출하는 방식은 위와 같음.
    
    Pageable pageable = PageRequest.of(0, 10, Sort.by(Direction.DESC, "gno")); // Sort.by 를 이용해서 Order by 등등 
    
    // Q도메인 관련 객체를 취득하는 방식. 제너레이티드 된 퍼블릭 필드로서 가져옴. 싱글턴스러운 파이널 선언자에서 가져옴. 이 안에 있던 애들을 자기 거처럼 씀.
    QGuestbookEntitiy qGuestbookEntitiy = QGuestbookEntitiy.guestbookEntitiy; 
    // 
    String Keyword = "";
    // 참거짓의 평가식은 where 절에서 사용하는 것. 빌더로서 정의해서 사용함. 필드에 대한 정의 정의 정의를 통해 사용함.
    // 결국 불린값이 필요한 거고, 간단히 논리값이라기 보다 어떤 구조가 필요하기 때문에 불린 빌더로서 사용하는 것.
    BooleanBuilder builder = new BooleanBuilder();
    // 불린 익스프레션 타입으로 리턴하는 것. 즉 이것이 contatin. -- expression 자체가 predicate 상속 객체이다.
    // like, notlike 등등 다양한 쿼리문들을 지정해 줄 수 있다. 
    // 즉 이것이 실제 where 절에 적용되는 논리값(불린빌더)
    BooleanExpression expression = qGuestbookEntitiy.title.contains(Keyword);
    // expression = 아래로 쭉 내리면서 그냥 123 붙이면서 추가적으로 선언해도 문제될 것이 없긴 함.
    // and, andAnyOf(다중 조건을 사용할 수 있다), andNot 등등 
    // where 절에서 다중 행 서브쿼리를 실행할 때 사용했던 키워드들. any, all이 이에 해당함.
    // builder.and(expression); // 체이닝 타입의 기본 리턴 타입은 자기 자신임. 따라서 꼭 체이닝을 통해 구현하지 않아도. 
    // builder.and(expression).or(expression);
    builder.and(expression);
    builder.or(qGuestbookEntitiy.writer.contains(Keyword)); // 저번에 있던 T, C, W 을 구현한다고 했을 때 or 를 활용하면 된다?? -- 이런 식으로.
    // 이런 식으로 사용해도 차이가 없다는 것임. 자바 수업을 진행했을 때 스트링 빌더, 스트링 버퍼에서 사용했던 것과 비슷함.

    Page<GuestbookEntitiy> result = repository.findAll(builder, pageable);
    result.forEach(log::info);

  }
}
