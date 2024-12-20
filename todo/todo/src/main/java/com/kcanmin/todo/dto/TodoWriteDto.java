package com.kcanmin.todo.dto;

import com.kcanmin.todo.domain.TodoEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@ToString
public class TodoWriteDto {
  private String task;
  
  public TodoWriteDto(TodoEntity entity){
    task = entity.getTask();
  }

  public TodoEntity toEntity(){
    return new TodoEntity().builder().task(task).build();
  }

}
