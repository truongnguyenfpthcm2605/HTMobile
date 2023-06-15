package com.poly.app.service;

import java.util.List;
import java.util.Optional;

import com.poly.app.enity.OrdersDetail;

public interface OrdersDetailService {
	OrdersDetail save(OrdersDetail orders);
	OrdersDetail update(OrdersDetail orders);
	Optional<OrdersDetail> findById(Integer id);
	List<OrdersDetail> findAll();
	void deleteById(Integer id);
	Optional<List<OrdersDetail>> findByOrders(Integer idOrders);
	 List<OrdersDetail> findOrdersDetailsByUserId(Integer userId);
	
}
