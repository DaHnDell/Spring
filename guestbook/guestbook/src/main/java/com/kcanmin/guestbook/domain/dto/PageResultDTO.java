package com.kcanmin.guestbook.domain.dto;

import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString(exclude = "dtoList")
public class PageResultDTO<D, E> {
  private List<D> dtoList;

  private int totalPage;
  private int page, size;
  private int start, end;
  private boolean prev, next;

  private List<Integer> pageList; // start, end 값을 포함한 리스트 값이 있지 않을까?

  public PageResultDTO(Page<E> result, Function<E, D> fn){ // D => GuestbookDTO, E=> GuestbookEntity ? 다른 곳에서 이걸 사용하거나 직접 명시해 줘야 함.
    dtoList = result.stream().map(fn).toList();
    // 여기서 Entity 부분이 뭔지 잘 모르겠음.
    totalPage = result.getTotalPages();
    
    Pageable pageable = result.getPageable();
    page = pageable.getPageNumber() + 1;
    size = pageable.getPageSize();

    int tempEnd = (int)(Math.ceil(page / 10.0))*10;
    start = tempEnd - 9;
    prev = start > 1;

    end = totalPage > tempEnd ? tempEnd : totalPage;
    next = totalPage > tempEnd;

    pageList = IntStream.rangeClosed(start, end).boxed().toList();
  } // 유일 생성자.
}
