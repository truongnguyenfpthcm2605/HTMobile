package com.poly.app.Impl;

import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.poly.app.enity.Users;
import com.poly.app.repository.UsersRepository;
import com.poly.app.service.UsersService;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {
	private final  UsersRepository dao;

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	@Override
	public Users save(Users users) {
		if (findByEmail(users.getEmail())!=null) {
		    return null;
		}
		return dao.save(users);
	}
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	@Override
	public Users update(Users users) {
		return dao.save(users);
	}

	@Override
	public Optional<Users> findById(Integer id) {
		return dao.findById(id);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	@Override
	public void deleteById(Integer id) {
		dao.deleteById(id);
	}

	@Override
	public List<Users> findAll() {
		return dao.findAll();
	}

	@Override
	public List<Users> findAll(Sort sort) {
		return dao.findAll(sort);
	}

	@Override
	public Page<Users> findAll(Pageable pageable) {
		return dao.findAll(pageable);
	}

	@Override
	public Users checkLogin(String email, String password) {
		return dao.checkLogin(email, password);
	}

	@Override
	public Users findByEmail(String email) {
		return dao.getByEmail(email);
	}

	@Override
	public Integer getToTalNewUser() {
		return dao.getToTalNewUser();
	}

	@Override
	public List<Users> findByKeywords(String keyword) {
		return dao.findByKeywords(keyword);
	}

	@Override
	public Optional<List<Users>> findByActive(Boolean active) {
		return dao.findByActive(active);
	}

	@Override
	public List<Users> findBySortAndActive(Boolean active,Sort sort) {
		return dao.findBySortAndActive(active, sort);
	}

}
