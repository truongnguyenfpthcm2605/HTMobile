package com.poly.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.app.enity.Categories;

public interface CategoriesRepository extends JpaRepository<Categories, Integer> {

}
