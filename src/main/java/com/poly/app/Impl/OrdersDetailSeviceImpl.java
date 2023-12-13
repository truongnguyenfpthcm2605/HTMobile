package com.poly.app.Impl;

import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.app.enity.OrdersDetail;
import com.poly.app.repository.OrdersDetailRepository;
import com.poly.app.service.OrdersDetailService;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrdersDetailSeviceImpl implements OrdersDetailService {

	private final  OrdersDetailRepository dao;
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	@Override
	public OrdersDetail save(OrdersDetail orders) {
		return dao.save(orders);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	@Override
	public OrdersDetail update(OrdersDetail orders) {
		return dao.save(orders);
	}

	@Override
	public Optional<OrdersDetail> findById(Integer id) {
		return dao.findById(id);
	}

	@Override
	public List<OrdersDetail> findAll() {
		return dao.findAll();
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	@Override
	public void deleteById(Integer id) {
		dao.deleteById(id);
	}

	@Override
	public Optional<List<OrdersDetail>> findByOrders(Integer idOrders) {
		return dao.findByOrders(idOrders);
	}

	@Override
	public List<OrdersDetail> findOrdersDetailsByUserId(Integer userId) {
		return dao.findOrdersDetailsByUserId(userId);
	}


}
