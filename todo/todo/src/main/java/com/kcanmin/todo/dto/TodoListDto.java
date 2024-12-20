package com.kcanmin.todo.dto;

import com.kcanmin.todo.domain.TodoEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class TodoListDto {
  private Long id;
  private String task;
  private boolean done;
  
  public TodoListDto(TodoEntity entity){
    // new TodoListDto(entity.getId(), entity.getTask(), entity.isDone());
    id = entity.getId();
    task = entity.getTask();
    done = entity.isDone();
  }

  // public TodoListDto(TodoEntity entity){
  //   new TodoListDto().builder().id(entity.getId()).task(entity.getTask()).done(entity.isDone()).build();
  // }

  public TodoEntity toEntity(){
    return new TodoEntity().builder().id(id).task(task).done(done).build();
  }

}
