package com.poly.app.Impl;

import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.poly.app.enity.Categories;
import com.poly.app.repository.CategoriesRepository;
import com.poly.app.service.CategoriesService;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CategoriesServiceImpl implements CategoriesService {

	private final  CategoriesRepository dao;

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	@Override
	public Categories save(Categories categories) {
		return dao.save(categories);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
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

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	@Override
	public void deleteById(Integer id) {
		dao.deleteById(id);
	}
}
