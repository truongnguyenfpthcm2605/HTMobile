package com.poly.app.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import com.poly.app.dto.CartItem;
import com.poly.app.service.ShoppingCartService;


@Service
@SessionScope
public class ShoppingCartServiceImpl implements ShoppingCartService {
	public static Map<Integer, CartItem> map = new HashMap<>();
	
	@Override
	public void add(CartItem cart) {
		CartItem cartExsits = map.get(cart.getId());
        if(cart.getQty()>10) cart.setQty(9);
        if(cartExsits==null) {
            map.put(cart.getId(), cart);
		}else {
            cartExsits.setQty(cartExsits.getQty()+1);
		}
		
	}

	@Override
	public void remove(Integer id) {
		map.remove(id);
	}

	@Override
	public CartItem update(Integer id, int qty) {
		CartItem cartExsits = map.get(id);
		cartExsits.setQty(qty);
		return cartExsits;
	}

	@Override
	public void clear() {
		map.clear();
	}

	@Override
	public List<CartItem> getAllCartItem() {
		return new ArrayList<>(map.values());
	}

	@Override
	public int getCount() {
		return map.values().size();
	}

	@Override
	public double getTotal() {
		return map.values().stream().mapToDouble(item -> item.getPrice()*item.getQty()).sum();
	}


}
