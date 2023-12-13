package com.poly.app.Impl;

import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.poly.app.enity.Product;
import com.poly.app.repository.ProductRepository;
import com.poly.app.service.ProductService;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl  implements ProductService{

	private final ProductRepository dao;
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	@Override
	public Product save(Product product) {
		return dao.save(product);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	@Override
	public Product update(Product product) {
		return dao.save(product);
	}

	@Override
	public Optional<Product> findById(Integer id) {
		return dao.findById(id);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	@Override
	public void deleteByid(Integer id) {
		dao.deleteById(id);
		
	}
	@Override
	public List<Product> findAll() {
		return dao.findAll();
	}
	@Override
	public Page<Product> findAll(Pageable pageable) {
		return dao.findAll(pageable);
	}

	@Override
	public List<Product> findProductsByCategoryId(Integer categoryId, Integer getproduct) {
		List<Product> products = dao.findProductsByCategoryId(categoryId);
        return products.subList(0, Math.min(products.size(), getproduct)); 
	}

	@Override
	public Page<Product> findByKeywords(String keywords, Pageable pageable) {
		return dao.findByKeywords(keywords, pageable);
	}

	@Override
	public List<Product> findAll(Sort sort) {
		return dao.findAll(sort);
	}

	@Override
	public List<Object[]> getInventoryByCategory() {
			return dao.getInventoryByCategory();
	}

	@Override
	public Integer getTotalQuantitySold() {
		return dao.getTotalQuantitySold();
	}

	@Override
	public List<Product> findByKeywordsSearch(String key) {
		return dao.findByKeywordsSearch(key);
	}

	@Override
	public Optional<List<Product>> findByProductMonth(Integer month) {
		return dao.findByProductMonth(month);
	}

	@Override
	public List<Product> findBycategory(Integer id) {
		return dao.findBycategory(id);
	}

	@Override
	public List<Product> findTop10ByViewsDesc() {
		return dao.findTop10ByViewsDesc();
	}

}
