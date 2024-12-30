package com.kcanmin.guestbook;

import static org.mockito.ArgumentMatchers.isNull;

import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.kcanmin.guestbook.domain.entity.GuestbookEntitiy;
import com.kcanmin.guestbook.repository.GuestbookRepository;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
class GuestbookApplicationTests {
	@Autowired
	private GuestbookRepository repository;
	@Transactional
	@Test
	public void contextLoads() {
		log.info("QueryDSL_Test");
	}

	@Transactional
	@Test
	public void TestInsert(){
		repository.saveAll(
			IntStream.rangeClosed(1, 300).mapToObj(i->{
				return GuestbookEntitiy.builder()
				.title("제목"+i)
				.content("내용"+i)
				.writer("작성자"+i%10)
				.build();
			}).toList()
		);
	}
	@Transactional
	@Test
	public void testSelectList(){
		log.info(repository.findById(1L));
	}
	@Transactional
	@Test
	public void testUpdateList(){
		Long gno = 1L;
		log.info(repository.findById(gno));
		Optional<GuestbookEntitiy> gbe = repository.findById(gno);
		if(!gbe.isPresent()){
			return;
		}
		// repository.saveAndFlush(gbe);
		// pk가 있으면 있는 대로 자동으로 업데이트 함 
		// GuestbookEntitiy entity = gbe.get();
		gbe.ifPresent(entity-> {
			GuestbookEntitiy modEntitiy = GuestbookEntitiy
			.builder()
				.gno(1L)
				.title(entity.getTitle())
				.content("수정 내용")
				.writer(entity.getWriter())
			.build();
			repository.save(modEntitiy);
		});

		// GuestbookEntitiy modEntitiy = GuestbookEntitiy
		// .builder()
		// 	.gno(1L)
		// 	.title(entity.getTitle())
		// 	.content("수정 내용")
		// 	.writer(entity.getWriter())
		// .build();
		// repository.save(modEntitiy);
		// String title = gbe.getTitle();
		// String writer = gbe.getWriter();

		// Optional<TodoEntity> entity = repository.findById(id);
    // entity.ifPresent(e->{e.setDone(true); repository.save(e);}); // 조회 후 save 하는 방식. 아무래도 이게 조금 더 정당한 느낌이 든다.
    // repository.save(TodoEntity.builder().id(id).done(true).task("task").build());
    // repository.updateTodoDoneById(id);
    // Optional.of(manager.find(TodoEntity.class, id).ifPresent(e->e.setDone(true))); // 이 부분의 코드 해석이 궁금합니다,,

		// log.info(gbe.builder().writer(null).title(null).content("수정된 내용").build());
	}


}
