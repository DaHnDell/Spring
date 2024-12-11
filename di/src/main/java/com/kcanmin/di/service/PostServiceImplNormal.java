package com.kcanmin.di.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.kcanmin.di.vo.Post;

import lombok.extern.log4j.Log4j2;


@Log4j2
@Service
public class PostServiceImplNormal implements PostService {

    @Override
    public List<Post> list() {
        List<Post> list = new ArrayList<>();
        list.add(Post.builder().pno(1L).title("일반 게시판").writer("작성자").build());
        list.add(Post.builder().pno(1L).title("일반 게시판").writer("작성자").build());
        return list;
    }

    @Override
    public void write(Post post) {
        log.info(getClass().getSimpleName() + "write().call");
    }
    
}
