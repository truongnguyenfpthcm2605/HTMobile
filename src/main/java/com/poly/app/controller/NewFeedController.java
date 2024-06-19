package com.poly.app.controller;

import com.poly.app.service.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.app.Impl.ShoppingCartServiceImpl;

@Controller
@RequestMapping("newfeed")
@RequiredArgsConstructor
public class NewFeedController {
	private final ShoppingCartService shoppingCartServiceImpl;
	@GetMapping("views")
	public String index() {
		return "newfeed";
	}

	@ModelAttribute("numberCartItem")
	public int getnumberCartItem() {
		return shoppingCartServiceImpl.getCount() != 0 ? shoppingCartServiceImpl.getCount() : 0;
	}
	
}
