package com.kcanmin.member_post.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.kcanmin.member_post.mapper.MemberMapper;
import com.kcanmin.member_post.vo.Member;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MemberServiceImpl implements MemberService {
	private MemberMapper memberMapper;
	
	@Override
	public int register(Member member) {
		// try(SqlSession session = MybatisInIt.getInstance().sqlSessionFactory().openSession(true)){
			// 	return mapper.insert(member);
			// }
		return memberMapper.insert(member);
	}

	@Override
	public Member findBy(String id) {
			return memberMapper.selectOne(id);
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
