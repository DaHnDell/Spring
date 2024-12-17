package com.kcanmin.member_post.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.type.Alias;
// import org.springframework.boot.context.properties.bind.ConstructorBinding;

// import lombok.AllArgsConstructor;
// import lombok.Builder;
import lombok.Data;

@Data
// @Builder
// @AllArgsConstructor
@Alias("post")
public class Post {
	private Long pno; // 게시글 번호
	private String title; // 게시글 제목
	private String writer; // 게시글 작성자
	private String content; // 게시글 내용
	private Long viewCnt; // 게시글 조회수
	private Date regDate; // 게시글 등록일
	private Date updateDate;
	private Integer cno;
	private Boolean attachFlag;
	// @Builder.Default
	private List<Attach> attachs = new ArrayList<>();

	// @ConstructorBinding
	public Post(Long pno, String title, String writer, String content, Long viewCnt, Date regDate, Date updateDate,
	Integer cno, Boolean attachFlag) {
		super();
		this.pno = pno;
		this.title = title;
		this.writer = writer;
		this.content = content;
		this.viewCnt = viewCnt;
		this.regDate = regDate;
		this.updateDate = updateDate;
		this.cno = cno;
		this.attachFlag = attachFlag;
	}
	
//	public final Post() {
//		
//	}
//	private Post(Long pno, String title, String writer, String content, Long viewCnt, Date regDate, Date updateDate, ) {
//		
//		this.pno = pno;
//		this.title = title;
//		this.writer = writer;
//		this.content = content;
//		this.viewCnt = viewCnt;
//		this.regDate = regDate;
//		this.updateDate = updateDate;
//	}
	
//	public static P builder() {
//		return new P();
//	}
//	
//	public static class P {
//		Long pno;
//		String title;
//		String writer;
//		String content;
//		Long viewCnt;
//		Date regDate;
//		Date updateDate;
//		
//		public P pno(Long pno) {
//			this.pno = pno;
//			return this;
//		}
//		public P title(String title) {
//			this.title = title;
//			return this;
//		}
//		public P writer(String writer) {
//			this.writer = writer;
//			return this;
//		}
//		public P content(String conent) {
//			this.content = conent;
//			return this;
//		}
//		public P viewCnt(Long viewCnt) {
//			this.viewCnt = viewCnt;
//			return this;
//		}
//		public P regDate(Date regDate) {
//			this.regDate = regDate;
//			return this;
//		}
//
//		public P update(Date updateDate) {
//			this.updateDate = updateDate;
//			return this;
//		}
//
//		public Post build() {
// 			return new Post(pno, title, writer, content, viewCnt, regDate, updateDate);
//		}
//	}
//	

}
