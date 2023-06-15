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
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.ToString;
@Data
@Entity
@Table(name = "orders")
public class Orders {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotEmpty(message = "Không được bỏ trống địa chỉ ")
	private String address;
	 @Pattern(regexp = "(03|09|08|07)\\d{8}", message = "Số điện thoại không hợp lệ")
	private String phone;
	private String status = "Đã Đặt Hàng";
	private Date createday;
	private String notes;
	private String voucher;
	
	@ManyToOne
	@JoinColumn(name = "usersid", referencedColumnName = "id")
	private Users users;
	
	@OneToMany(mappedBy = "orders")
	List<OrdersDetail> ordersDetails;
	
	@Override
	public String toString() {
		return "";
	}
	
}
