package com.poly.app.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.app.enity.Categories;
import com.poly.app.repository.CategoriesRepository;
import com.poly.app.service.CategoriesSevice;

@Service
public class CategoriesServiceImpl implements CategoriesSevice {
	
	@Autowired
	CategoriesRepository dao;

	@Override
	public Categories save(Categories categories) {
		return dao.save(categories);
	}

	@Override
	public Categories update(Categories categories) {
		return dao.save(categories);
	}

	@Override
	public Optional<Categories> findById(Integer id) {
		return dao.findById(id);
	}

	@Override
	public List<Categories> findAll() {
		return dao.findAll();
	}

	@Override
	public void deleteById(Integer id) {
		dao.deleteById(id);
		
	}
	
	

}
