package com.kcanmin.member_post.vo;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Alias("reply")
public class Reply {
	private Long rno;
	private String content;
	private Date regDate;
	private Date updateDate;
	private Boolean hidden;
	private Integer likes;
	private String writer;
	private Long pno;
}
