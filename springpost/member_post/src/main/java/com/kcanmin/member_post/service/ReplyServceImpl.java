package com.kcanmin.member_post.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.kcanmin.member_post.dto.ReplyCri;
import lombok.AllArgsConstructor;
import com.kcanmin.member_post.mapper.ReplyMapper;
import com.kcanmin.member_post.vo.Member;
import com.kcanmin.member_post.vo.Reply;

// @NoArgsConstructor(access = AccessLevel.PRIVATE)
@Service
@AllArgsConstructor
public class ReplyServceImpl implements ReplyService{
	private ReplyMapper mapper;

	// 게터 어노테이션 + 인스턴스로 싱글턴 구현하기.
	// @Getter
	// private static ReplyService instance = new ReplyServceImpl();
	
	@Override
	public int write(Reply reply) {
			return mapper.insert(reply);
	}

	@Override
	public int modify(Reply reply) {
			return mapper.update(reply);
	}

	@Override
	public int remove(Long rno) {
		return mapper.delete(rno);
	}

	@Override
	public int removeAll(Long pno) {
			return mapper.deleteAll(pno);
	}

	@Override
	public Reply findBy(Long rno) {
			return mapper.selectOne(rno);
	}

	@Override
	public Map<String, List<Reply>> list(Long pno, ReplyCri cri, Object writer) {
			Map<String, List<Reply>> map = new HashMap<String, List<Reply>>();
			map.put("list", mapper.selectList(pno, cri));
			if(writer != null) {
				Reply reply = new Reply();
				reply.setWriter(((Member)writer).getId());
				reply.setPno(pno);
				map.put("myList", mapper.selectListByMe(reply));
			}
			return map;
	}
	
//	@Override
//	public List<Reply> list(Long pno, ReplyCri cri) {
//		try(SqlSession session = MybatisInIt.getInstance().sqlSessionFactory().openSession(true)){
//			ReplyMapper mapper = session.getMapper(ReplyMapper.class);
//			return mapper.selectList(pno, cri);
//		}
//	}

	@Override
	public List<Reply> myList(String id) {
			return mapper.selectMyList(id);
	}


	
}
