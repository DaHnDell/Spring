package com.kcanmin.member_post.service;

import java.util.List;
import java.util.Map;

import com.kcanmin.member_post.dto.ReplyCri;
import com.kcanmin.member_post.vo.Reply;

public interface ReplyService {
	
	int write(Reply reply); 
	int modify(Reply reply);
	int remove(Long rno);
	int removeAll(Long pno);
	Reply findBy(Long rno);
//	List<Reply> list(Long pno, ReplyCri cri); // 댓글은 포스트에 종속된 존재이기 때문임.
	List<Reply> myList(String id);
	Map<String, List<Reply>> list(Long pno, ReplyCri cri, Object writer);
//	int increaseLikes(Long rno);
	
	
	
}
