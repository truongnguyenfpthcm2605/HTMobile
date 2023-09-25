package com.poly.app.enity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;
import lombok.ToString;
@Data
@Entity
@ToString
@Table(name = "ordersDetail")
public class OrdersDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne
	@JoinColumn(name = "ordersid", referencedColumnName = "id")
	private Orders orders;

	@ManyToOne
	@JoinColumn(name = "productid", referencedColumnName = "id")
	private Product product;
	private Double price;
	private Integer quantity;

}
