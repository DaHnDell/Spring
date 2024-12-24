package com.kcanmin.guestbook.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.kcanmin.guestbook.domain.dto.GuestbookListDTO;
import com.kcanmin.guestbook.domain.dto.GuestbookModifyDTO;
import com.kcanmin.guestbook.domain.dto.GuestbookViewDTO;
import com.kcanmin.guestbook.domain.dto.GuestbookWriteDTO;
import com.kcanmin.guestbook.domain.entity.GuestbookEntitiy;
import com.kcanmin.guestbook.repository.GuestbookRepository;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class GuestbookServiceImpl implements GuestbookService{
  private GuestbookRepository repository; // 리포지토리 가져와~

  public List<GuestbookListDTO> list(){
    return repository.findAll().stream().map(GuestbookListDTO::new).toList();
  };

  @Override
  public GuestbookViewDTO get(Long gno) {
    Optional<GuestbookEntitiy> opt = repository.findById(gno);
    if(!opt.isPresent()){
      return null;
    }
    return new GuestbookViewDTO(opt.get());
    // 하나 조회해서 반환하는것도 어렵네,,
    // return repository.findById(gno).get().;
    // return new GuestbookViewDTO(repository.findById(gno).) 
    // 가져오는 애가 옵셔널이면 처음부터 생성자를 optional로 하면 된다
  }

  @Override
  public void write(GuestbookWriteDTO dto){
    repository.save(dto.toEntitiy());
  };

  @Override
  public void modify(GuestbookModifyDTO dto){
    // repository.
    repository.save(dto.toEntitiy());
  }

  @Override
  public void remove(Long gno){
    repository.deleteById(gno);
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
