package com.kcanmin.member_post.service;

import java.util.List;

import com.kcanmin.member_post.vo.Member;

public interface MemberService {
	
	// 회원 가입
	int register(Member member);
	// 인터페이스를 정의한다는 건 시그니처를 정의한다는 것. 접근제한자, 파라미터, 반환타입 등등
	// 구현부의 내용은 얼마든지 바뀌어도 상관이 없다. 하지만 인터페이 내부를 정의해주는 겉의 내용들은
	// 한번 정해지면 웬만하면 바뀌지 않는다.
	
	// 단일 조회
	Member findBy(String id);
	
	// 로그인
	boolean login(String id, String pw);
	
	// 회원 목록
	List<Member> list();
	
	// 회원 탈퇴
	boolean remove(String id);
	
	// 회원 정보 수정 
	boolean modify(Member member);
	
	// 상속의 다형성은 자주 안써도, 인터페이스의 다형성은 생각보다 일상적으로 많이 쓰임.
	
}
