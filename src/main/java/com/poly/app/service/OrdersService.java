package com.poly.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;

import com.poly.app.enity.Orders;

public interface OrdersService {
		Orders save(Orders orders);
		Orders update(Orders orders);
		Optional<Orders> findById(Integer id);
		List<Orders> findAll();
		void deleteById(Integer id);
		Integer getTotalOrders();
		List<Object[]> findListOrders();
		Optional<List<Orders>> findByKeywords(String key);
		List<Orders> findAll(Sort sort);
		Optional<List<Orders>> findByStatus(String status);
		Optional<List<Orders>> findByMoths(Integer month);
}
