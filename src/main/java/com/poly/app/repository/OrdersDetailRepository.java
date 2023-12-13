package com.poly.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.app.enity.OrdersDetail;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersDetailRepository extends JpaRepository<OrdersDetail, Integer> {
	@Query("SELECT od FROM Orders o JOIN o.ordersDetails od WHERE o.id = ?1")
	Optional<List<OrdersDetail>> findByOrders(Integer idOrders);
	
	 @Query("SELECT od FROM OrdersDetail od JOIN FETCH od.product p"
	 		+ " JOIN od.orders o JOIN o.users u WHERE u.id = :userId")
	    List<OrdersDetail> findOrdersDetailsByUserId(Integer userId);
}	
