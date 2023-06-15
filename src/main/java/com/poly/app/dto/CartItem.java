package com.poly.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {
		Integer id;
		String name;
		String img;
		double price;
		int qty= 1;
}
