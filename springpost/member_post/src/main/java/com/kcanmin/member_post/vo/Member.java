package com.kcanmin.member_post.vo;

import java.util.Date;

import lombok.Data;
// 클래스 메서드는 무조건 스태틱 키워드로 선언

@Data
public class Member {
	private String id;
	private String pw;
	private String name;
	private String email;
	private String roadAddr;
	private String detailAddr;
	private Date regDate;

	// 빌더 패턴을 강제시키는 생성자로서 굳이 public으로 할 이유가 없다.
	public Member() {

	}

	private Member(String id, String pw, String name, String email, String roadAddr, String detailAddr, Date regDate) {
		
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.email = email;
		this.roadAddr = roadAddr;
		this.detailAddr = detailAddr;
		this.regDate = regDate;
	}

	// 필드는 어지간하면 private, 메서드는 어지간하면 public
	public static M builder() {

		return new M();
	}
	
	public boolean getNum() {
		return true;
	}

	public static class M { // 객체 생성용으로 사용되는 내부 클래스, static 없으면 인스턴스 클래스가 됨.
		String id;
		String pw;
		String name;
		String email;
		String roadAddr;
		String detailAddr;
		Date regDate;

		public M id(String id) {
			this.id = id;
			return this;
		}

		public M pw(String pw) {
			this.pw = pw;
			return this;
		}

		public M name(String name) {
			this.name = name;
			return this;
		}

		public M email(String email) {
			this.email = email;
			return this;
		}

		public M roadAddr(String roadAddr) {
			this.roadAddr = roadAddr;
			return this;
		}

		public M detailAddr(String detailAddr) {
			this.detailAddr = detailAddr;
			return this;
		}

		public M regDate(Date regDate) {
			this.regDate = regDate;
			return this;
		}

		public Member build() {
 			return new Member(id, pw, name, email, roadAddr, detailAddr, regDate);
		}

	}
	

}
