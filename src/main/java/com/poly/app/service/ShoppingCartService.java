package com.poly.app.service;
import java.util.List;

import com.poly.app.dto.CartItem;

public interface ShoppingCartService {
		void add(CartItem id);
		void remove(Integer id);
		CartItem update(Integer id, int qty);
		void clear();
		List<CartItem> getAllCartItem();
		int getCount();
		double getTotal();

}
