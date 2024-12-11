package com.kcanmin.di.service;

import java.util.List;

import com.kcanmin.di.vo.Post;

public interface PostService {
    void write(Post post);
    List<Post> list();
}
