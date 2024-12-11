package com.kcanmin.di.service;

import java.util.List;

import com.kcanmin.di.vo.Post;

public interface PostService {
    default void write(Post post){
        
    };
    List<Post> list();
}
