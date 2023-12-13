package com.poly.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.app.enity.Categories;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriesRepository extends JpaRepository<Categories, Integer> {
}
