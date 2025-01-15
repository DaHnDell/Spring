package com.kcanmin.club.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kcanmin.club.entity.Attach;

@Repository
public interface AttachRepository extends JpaRepository<Attach, String>{
  
}
