package com.kcanmin.todo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kcanmin.todo.domain.TodoEntity;

public interface TodoRepository extends JpaRepository<TodoEntity, Long>{
  @Modifying
  @Query("update todo t set t.done = true where t.id = :id") // jpql 문법 꼭 지키기.
  int updateTodoDoneById(@Param("id") Long id );//파라미터 바인딩

  List<TodoEntity> findByOrderByTaskDescIdAsc();
}
