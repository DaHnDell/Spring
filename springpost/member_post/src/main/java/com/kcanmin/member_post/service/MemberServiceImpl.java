package com.kcanmin.member_post.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.kcanmin.member_post.mapper.MemberMapper;
import com.kcanmin.member_post.utils.MybatisInIt;
import com.kcanmin.member_post.vo.Member;

public class MemberServiceImpl implements MemberService {
	@Override
	public int register(Member member) {
		try(SqlSession session = MybatisInIt.getInstance().sqlSessionFactory().openSession(true)){
			MemberMapper mapper = session.getMapper(MemberMapper.class);
			return mapper.insert(member);
		}
	}

	@Override
	public Member findBy(String id) {
		try(SqlSession session = MybatisInIt.getInstance().sqlSessionFactory().openSession(true)){
			MemberMapper mapper = session.getMapper(MemberMapper.class);
			return mapper.selectOne(id);
		}
	}

	@Override
	public boolean login(String id, String pw) {
		Member m = findBy(id);
		return m != null && m.getPw().equals(pw);
	}

	@Override
	public List<Member> list() {
		return null;
	}

	@Override
	public boolean remove(String id) {
		return false;
	}

	@Override
	public boolean modify(Member member) {
		return false;
	} 
	// implement 구현
	
	
}
