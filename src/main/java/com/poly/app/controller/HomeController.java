package com.poly.app.controller;

import java.util.List;

import com.poly.app.service.ProductService;
import com.poly.app.service.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.poly.app.Impl.ProductServiceImpl;
import com.poly.app.Impl.ShoppingCartServiceImpl;
import com.poly.app.enity.Product;

@Controller
@RequiredArgsConstructor
public class HomeController {

	private final ProductService productServiceImpl;
	private final ShoppingCartService shoppingCartServiceImpl;

	@ModelAttribute("numberCartItem")
	public int getnumberCartItem() {
		return shoppingCartServiceImpl.getCount() != 0 ? shoppingCartServiceImpl.getCount() : 0;
	}


	@GetMapping("index")
	public String index(Model model) {
		List<Product> list = productServiceImpl.findTop10ByViewsDesc();
		List<Product> list2 = productServiceImpl.findBycategory(1);
		model.addAttribute("listNew", list  );
		model.addAttribute("listIphone", list2  );
		return "index";
	}

	@GetMapping("favorite")
	public String favorite() {
		return "favorite";
	}
}
