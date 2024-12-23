package com.kcanmin.todo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kcanmin.todo.domain.TodoEntity;
import com.kcanmin.todo.domain.TodoRepository;
import com.kcanmin.todo.dto.TodoListDto;
import com.kcanmin.todo.dto.TodoWriteDto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class TodoService {
  private final TodoRepository repository;
  // 목록 조회

  public List<TodoListDto> list() {
    return repository.findAll().stream().map(TodoListDto::new).toList();
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

}
