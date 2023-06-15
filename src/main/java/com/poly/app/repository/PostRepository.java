package com.poly.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.app.enity.Post;

public interface PostRepository  extends JpaRepository<Post, Integer>{
		
}
