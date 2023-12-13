package com.poly.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.app.enity.Orders;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Integer> {
	
		@Query("select count(o) from Orders o")
		Integer getTotalOrders();
		
		@Query("SELECT o.users.fullname, COUNT(o) FROM Orders o "
		        + "GROUP BY o.users.fullname "
		        + "ORDER BY COUNT(o) DESC")
		List<Object[]> findListOrders();
		
		@Query("select o from Orders o where  o.users.fullname Like ?1")
		Optional<List<Orders>> findByKeywords(String key);
		
		Optional<List<Orders>> findByStatus(String status);
		
		@Query("select o from Orders o where MONTH(o.createday) = ?1")
		Optional<List<Orders>> findByMoths(Integer month);
		
	
}
