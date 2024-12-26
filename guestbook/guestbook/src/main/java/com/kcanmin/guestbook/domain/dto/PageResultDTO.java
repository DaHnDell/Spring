package com.kcanmin.guestbook.domain.dto;

import java.util.List;
import java.util.function.Function;

import org.springframework.data.domain.Page;

import lombok.Data;


@Data
public class PageResultDTO<D, E> {
  private List<D> dtoList;
  public PageResultDTO(Page<E> result, Function<E, D> fn){ // D => GuestbookDTO, E=> GuestbookEntity ? 다른 곳에서 이걸 사용하거나 직접 명시해 줘야 함.
    dtoList = result.stream().map(fn).toList();
    // 여기서 Entity 부분이 뭔지 잘 모르겠음.
  }
}
