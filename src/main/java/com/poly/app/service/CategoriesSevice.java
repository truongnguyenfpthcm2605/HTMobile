package com.poly.app.service;

import java.util.List;
import java.util.Optional;


import com.poly.app.enity.Categories;

public interface CategoriesSevice {
		
	Categories save(Categories categories);
	Categories update(Categories categories);
	Optional<Categories> findById(Integer id);
	List<Categories> findAll();
	void deleteById(Integer id);
	
}
