package com.poly.app.enity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

import lombok.Data;

@Data
@Entity
@Table(name = "product")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String title;
	private Double pricecost;
	private Double price;
	private Integer ram;
	private Integer rom;
	private Integer pin;
	private String chip;
	private Integer camera;
	private Double screen;
	private Date createday;
	private Date updateday;

	private Integer quanityfinal;
	private Integer quantitysold;
	private String imgproduct;
	private String thumnail;

	private String description;
	private Boolean active;
	private Long views;
	
	@ManyToOne
	@JoinColumn(name = "categoryid", referencedColumnName = "id")
	private Categories category;
	
	@OneToMany(mappedBy = "product")
	List<OrdersDetail> ordersDetails;
	
	@OneToMany(mappedBy = "product")
	List<Share> shares;
	
	@OneToMany(mappedBy = "product")
	List<Favorite> favorites;
	
	@Override
	public String toString() {
		return "";
	}
	
}
