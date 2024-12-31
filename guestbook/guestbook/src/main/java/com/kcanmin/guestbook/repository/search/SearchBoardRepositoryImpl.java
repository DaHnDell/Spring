package com.kcanmin.guestbook.repository.search;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.kcanmin.guestbook.domain.entity.BoardEntity;
import com.kcanmin.guestbook.domain.entity.QBoardEntity;
import com.kcanmin.guestbook.domain.entity.QMemberEntity;
import com.kcanmin.guestbook.domain.entity.QReplyEntity;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPQLQuery;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class SearchBoardRepositoryImpl extends QuerydslRepositorySupport implements SearchBoardRepository{
  public SearchBoardRepositoryImpl(){
    super(BoardEntity.class);
  }

  @Override
  public BoardEntity search1() {
    log.info("첫 번째 검색 메서드입니다.");
    QBoardEntity board = QBoardEntity.boardEntity;
    QReplyEntity reply = QReplyEntity.replyEntity;
    QMemberEntity member = QMemberEntity.memberEntity;
    JPQLQuery<BoardEntity> jpqlQuery = from(board);
    jpqlQuery
      .leftJoin(member).on(board.member.eq(member))
      .leftJoin(reply).on(reply.board.eq(board));
    JPQLQuery<Tuple> tuple = 
    jpqlQuery
      .select(board, member.email, reply.count())
      .groupBy(board);
    // .where(board.bno.eq(105L)).select(board);
    log.info(tuple);
    List<Tuple> list = tuple.fetch();
    return null;
  }

  @Override
  public Page<Object[]> searchPage(String type, String keyword, Pageable pageable) {
    log.info("searchPage----------------------------------------");
    QBoardEntity board = QBoardEntity.boardEntity;
    QMemberEntity member = QMemberEntity.memberEntity;
    QReplyEntity reply = QReplyEntity.replyEntity;
    JPQLQuery<BoardEntity> jpqlQuery = from(board);
    jpqlQuery
      .leftJoin(member).on(board.member.eq(member))
      .leftJoin(reply).on(reply.board.eq(board));
    JPQLQuery<Tuple> tuple = 
    jpqlQuery
      .select(board, member.email, reply.count())
      .groupBy(board);

    BooleanBuilder booleanBuilder = new BooleanBuilder();
    BooleanExpression expression = board.bno.gt(0L);
    
    booleanBuilder.and(expression);

    if (type != null) {
      BooleanBuilder conditionBuilder = new BooleanBuilder();
      if(type.contains("T")){
        conditionBuilder.or(board.title.contains(keyword));
      }
      if(type.contains("C")){
        conditionBuilder.or(board.content.contains(keyword));
      }
      if(type.contains("W")){
        conditionBuilder.or(board.member.email.contains(keyword));
      }
      booleanBuilder.and(conditionBuilder);
    }

    tuple.where(booleanBuilder);
    
    // order by
    Sort sort = pageable.getSort();

    sort.stream().forEach(o->{
      Order direction = o.isAscending() ? Order.ASC : Order.DESC;
      String prop = o.getProperty();

      PathBuilder orderByExp = new PathBuilder<>(BoardEntity.class, "board") ;
      tuple.orderBy(new OrderSpecifier<>(direction, orderByExp.get(prop, String.class)));
    });
    
    
    tuple.groupBy(board);


    //page
    tuple.offset(pageable.getOffset());
    tuple.limit(pageable.getPageSize());

    List<Tuple> result = tuple.fetch();

    Long count = tuple.fetchCount();

    return new PageImpl<>(result.stream().map(t-> t.toArray()).toList(), pageable, 0);
  }

  

  
}
