package com.poly.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.app.enity.Favorite;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteRepository  extends JpaRepository<Favorite, Integer>{
}
