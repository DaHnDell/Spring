package com.kcanmin.guestbook.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.kcanmin.guestbook.domain.dto.GuestbookListDTO;
import com.kcanmin.guestbook.domain.dto.GuestbookModifyDTO;
import com.kcanmin.guestbook.domain.dto.GuestbookViewDTO;
import com.kcanmin.guestbook.domain.dto.GuestbookWriteDTO;
import com.kcanmin.guestbook.repository.GuestbookRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class GuestbookServiceImpl implements GuestbookService{
  private GuestbookRepository repository;

  public List<GuestbookListDTO> list(){
    return repository.findAll().stream().map(GuestbookListDTO::new).toList();
  };

  @Override
  public GuestbookViewDTO get(Long gno) {
    // 하나 조회해서 반환하는것도 어렵네,,
    // return repository.findById(gno).get().;
  }

  @Override
  public void write(GuestbookWriteDTO dto){
    repository.save(dto.toEntitiy());
  };

  // public void remove(Long gno){
  //   repository.deleteById(gno);
  // }

  @Override
  public void modify(GuestbookModifyDTO dto){
    // repository.
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
