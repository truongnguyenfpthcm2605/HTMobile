package com.poly.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.poly.app.enity.Product;

public interface ProductService {
	Product save(Product product);
	Product update(Product product);
	Optional<Product> findById(Integer id);
	void deleteByid(Integer id);
	List<Product> findAll();
	Page<Product> findAll(Pageable pageable);
	 List<Product> findProductsByCategoryId(Integer categoryId,Integer  getproduct);
	Page<Product> findByKeywords( String  keywords,Pageable pageable);
	List<Product> findAll(Sort sort);
	List<Object[]> getInventoryByCategory();
	Integer getTotalQuantitySold();
	List<Product> findByKeywordsSearch(String key);
	Optional<List<Product>> findByProductMonth(Integer month);
	List<Product> findBycategory(Integer id);
	List<Product> findTop10ByViewsDesc();
	
}
