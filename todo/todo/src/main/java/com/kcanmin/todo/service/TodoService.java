package com.kcanmin.todo.service;

import java.util.List;

import org.springframework.stereotype.Service;

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

  public void write(TodoWriteDto dto) {
    repository.save(dto.toEntity());
  }
  // 등록

  // 삭제
}
