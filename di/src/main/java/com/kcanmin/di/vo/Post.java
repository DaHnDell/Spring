package com.kcanmin.di.vo;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    private Long pno;
    private String title;
    private String writer;
    private LocalDateTime regDate;



    // {
        // Calendar cal = new GregorianCalendar(null) , 시간대 locale, 나노세컨드 이슈
    // }
}
