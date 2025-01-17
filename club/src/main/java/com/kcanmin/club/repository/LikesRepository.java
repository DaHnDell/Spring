package com.kcanmin.club.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kcanmin.club.entity.Likes;
import com.kcanmin.club.entity.composite.LikesId;

@Repository
public interface LikesRepository extends JpaRepository<Likes, LikesId>{
  
}
