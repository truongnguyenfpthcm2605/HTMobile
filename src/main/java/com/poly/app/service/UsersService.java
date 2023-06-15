package com.poly.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.poly.app.enity.Users;

public interface UsersService {
		Users save(Users users);
		Users update(Users users);
		Optional<Users> findById(Integer id);
		void deleteById(Integer id);
		List<Users> findAll();
		List<Users> findAll(Sort sort);
		Page<Users> findAll(Pageable pageable);
		Users checkLogin(String email, String password);
		Users findByEmail(String email);
		Integer getToTalNewUser();
		List<Users> findByKeywords(String keyword);
		Optional<List<Users>> findByActive(Boolean active);
		List<Users> findBySortAndActive(Boolean active,Sort sort);
		
}	
