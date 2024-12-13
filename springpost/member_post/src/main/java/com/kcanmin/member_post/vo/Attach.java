package com.kcanmin.member_post.vo;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Attach {
	private String uuid;
	private String origin;
	private String path;
	private boolean image;
	private Long pno;
}
