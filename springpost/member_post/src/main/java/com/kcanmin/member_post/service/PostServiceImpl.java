package com.kcanmin.member_post.service;

import java.util.List;

// import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kcanmin.member_post.dto.Criteria;
import com.kcanmin.member_post.mapper.AttachMapper;
import com.kcanmin.member_post.mapper.PostMapper;
import com.kcanmin.member_post.mapper.ReplyMapper;
import com.kcanmin.member_post.vo.Post;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@Transactional
@AllArgsConstructor
public class PostServiceImpl implements PostService{

	private PostMapper postMapper;
	private AttachMapper attachMapper;
	private ReplyMapper replyMapper;

	
	@Override
	public int write(Post post){
		// Optional<Integer> nullCno = OptionalInt(post.getCno());

		log.info(post);
		log.info(post.getCno());
		if(post.getCno() == null)	{
			post.setCno(2);
		}
		log.info(post.getCno());
		postMapper.insert(post);
		post.getAttachs().forEach(a->{
			a.setPno(post.getPno());
			attachMapper.insert(a);
		});
			return 0;
		}
	
	@Override
	public int modify(Post post) {
		return postMapper.update(post);
	}
	
	@Override
	public int remove(Long pno) {
		attachMapper.delete(pno);
		replyMapper.deleteAll(pno);
		return postMapper.delete(pno);
	}

	@Override
	public Post findBy(Long pno) {
		return postMapper.selectOne(pno);
	}

	@Override
	public List<Post> list(Criteria cri){
		return postMapper.selectList(cri);
	}
	
	@Override
	public int count(Criteria cri) {
		return postMapper.getCount(cri);
	}
	
	@Override
	public Post view(Long pno) {
		postMapper.increaseViewCount(pno);
		Post post = postMapper.selectOne(pno);
		post.setAttachs(attachMapper.selectList(pno));
		return postMapper.selectOne(pno);
	}
}
