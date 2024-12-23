package com.kcanmin.todo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
// import org.springframework.data.domain.ScrollPosition.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kcanmin.todo.domain.TodoEntity;
import com.kcanmin.todo.dto.TodoListDto;
import com.kcanmin.todo.dto.TodoWriteDto;
import com.kcanmin.todo.repository.TodoRepository;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
// import jakarta.persistence.criteria.Order;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class TodoService {
  private TodoRepository repository;
  private EntityManager manager;

  @PostConstruct
  public void init(){
    // this.repository = repository; // 생성자 내부에서 스스스로 주입 여부를 부탁하는 것.
    repository.saveAll(
      Stream.of(
        TodoEntity.builder().task("첫일").build(),
        TodoEntity.builder().task("둘일").build(),
        TodoEntity.builder().task("셋일").build()
      ).toList()
    );
  }
  
  // 목록 조회
  public List<TodoListDto> list() {
    // return repository.findAll().stream().map(TodoListDto::new).toList();
    // return repository.findAll(Sort.by(Direction.DESC, "id")).stream().map(TodoListDto::new).toList(); // 이 방식이 가독성도 지키고 안정성도 높음.
    // return repository.findByOrderByTaskDescIdAsc().stream().map(TodoListDto::new).toList();
    // return repository.findAll(Sort.by(Direction.DESC, "task", "id")).stream().map(TodoListDto::new).toList(); // task로 DESC 하고 id는 ASC로 하겠다는 뜻.
    return repository.findAll(Sort.by(Order.desc("task"), Order.asc("id"))).stream().map(TodoListDto::new).toList(); // task로 DESC 하고 id는 ASC로 하겠다는 뜻.
  }
  
  // 등록
  public void write(TodoWriteDto dto) {
    repository.save(dto.toEntity());
  }

  // 삭제
  public void remove(Long id) {
    // repository.delete(TodoEntity.builder().id(id).build()); //엔티티 호출하는 방식,
    repository.deleteById(id); //아이디 방식으로 전체삭제
    // 가장 쉬운 방법은 
  }

  // 수정
  @Transactional
  public void modify(Long id){
    // Optional<TodoEntity> entity = repository.findById(id);
    // entity.ifPresent(e->{e.setDone(true); repository.save(e);}); // 조회 후 save 하는 방식. 아무래도 이게 조금 더 정당한 느낌이 든다.
    // repository.save(TodoEntity.builder().id(id).done(true).task("task").build());
    // repository.updateTodoDoneById(id);
    // Optional.of(manager.find(TodoEntity.class, id).ifPresent(e->e.setDone(true))); // 이 부분의 코드 해석이 궁금합니다,,
    manager.find(TodoEntity.class, id).setDone(true);
  }
}
