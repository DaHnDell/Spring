package com.kcanmin.guestbook.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.kcanmin.guestbook.domain.dto.GuestbookDTO;
import com.kcanmin.guestbook.domain.dto.PageRequestDTO;
import com.kcanmin.guestbook.domain.dto.PageResultDTO;
import com.kcanmin.guestbook.domain.entity.GuestbookEntitiy;
import com.kcanmin.guestbook.domain.entity.QGuestbookEntitiy;
import com.kcanmin.guestbook.repository.GuestbookRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class GuestbookServiceImpl implements GuestbookService{
  @Autowired
  private GuestbookRepository repository; // 리포지토리 가져와~

  public List<GuestbookDTO> list(){
    // return repository.findAll().stream().map(GuestbookDTO::new).toList();
    return null;
  };

  // @Override
  // public GuestbookDTO get(Long gno) {
  //   Optional<GuestbookEntitiy> opt = repository.findById(gno);
  //   if(!opt.isPresent()){
  //     return null;
  //   }
  //   // return new GuestbookDTO(opt.get());
  //   return null;
  //   // 하나 조회해서 반환하는것도 어렵네,,
  //   // return repository.findById(gno).get().;
  //   // return new GuestbookViewDTO(repository.findById(gno).) 
  //   // 가져오는 애가 옵셔널이면 처음부터 생성자를 optional로 하면 된다
  // }

  @Override
  public Long write(GuestbookDTO dto){
    GuestbookEntitiy entitiy = toEntitiy(dto);
    log.info(entitiy);
    repository.save(entitiy);
    log.info(entitiy);
    return entitiy.getGno();
    // 작성하고 나서 작성한 글의 글 번호를 리턴할 예정.
  }

  @Override
  public void modify(GuestbookDTO dto){
    // repository.
    GuestbookEntitiy entitiy = toEntitiy(dto);
    repository.save(entitiy);
  }

  @Override
  public void remove(Long gno){
    repository.deleteById(gno);
  }

  @Override
  public PageResultDTO<GuestbookDTO, GuestbookEntitiy> list(PageRequestDTO dto) {
    Pageable pageable = dto.getPageable(Sort.by(Direction.DESC, "gno"));
    BooleanBuilder booleanBuilder = getSearch(dto);
    Page<GuestbookEntitiy> page = repository.findAll(booleanBuilder, pageable);
    Function<GuestbookEntitiy, GuestbookDTO> function = e-> toDTO(e);
    PageResultDTO<GuestbookDTO, GuestbookEntitiy> resultDTO = new PageResultDTO<>(page, function);
    return resultDTO;
  }

  @Override
  public GuestbookDTO read(Long gno) {
    Optional<GuestbookEntitiy> opt = repository.findById(gno);
    return opt.isPresent() ? toDTO(opt.get()) : null;
  }


  private BooleanBuilder getSearch(PageRequestDTO requestDTO){
    String type = requestDTO.getType();
    BooleanBuilder booleanBuilder = new BooleanBuilder();
    QGuestbookEntitiy qGuestbookEntitiy = QGuestbookEntitiy.guestbookEntitiy;
    BooleanExpression expression = qGuestbookEntitiy.gno.gt(0L);
    booleanBuilder.and(expression);
    if(type == null || type.trim().isEmpty()){
      return booleanBuilder;
    }
    BooleanBuilder conditionaBooleanBuilder = new BooleanBuilder();
    String keyword = requestDTO.getKeyword();
    if(type.contains("T")){
      conditionaBooleanBuilder.or(qGuestbookEntitiy.title.contains(keyword));
    }
    if(type.contains("C")){
      conditionaBooleanBuilder.or(qGuestbookEntitiy.content.contains(keyword));
    }
    if(type.contains("W")){
      conditionaBooleanBuilder.or(qGuestbookEntitiy.writer.contains(keyword));
    }
    booleanBuilder.and(conditionaBooleanBuilder);
    return booleanBuilder;
  }


  

  
  // @Transactional
  // public void modify(Long id){
	// 	Optional<GuestbookEntitiy> gbe = repository.findById(id);
	// 	if(!gbe.isPresent()){ // gbe 관련 객체가 있는지 없는지에 관한 조건식
	// 		return;
	// 	}
	// 	gbe.ifPresent(entity-> {
	// 		GuestbookEntitiy modEntitiy = GuestbookEntitiy
	// 		.builder()
	// 			.gno(id)
	// 			.title(entity.getTitle())
	// 			.content(entity.getContent())
	// 			.writer(entity.getWriter())
	// 		.build();
	// 		repository.save(modEntitiy);
	// 	});
  // }

  // 수정 부분 코드를 잘 모르겠음...!
}
