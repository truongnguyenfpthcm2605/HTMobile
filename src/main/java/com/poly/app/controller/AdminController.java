package com.poly.app.controller;


import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.app.Impl.OrdersServiceImpl;
import com.poly.app.Impl.ProductServiceImpl;
import com.poly.app.Impl.UsersServiceImpl;

@Controller
@RequestMapping("admin")
@RequiredArgsConstructor
public class AdminController {
	private final ProductServiceImpl productServiceImpl;
	private final OrdersServiceImpl ordersServiceImpl;
	private final UsersServiceImpl usersServiceImpl;
	
	@GetMapping("overview")
	public String overview(Model model) {
		List<Object[]> list =  productServiceImpl.getInventoryByCategory();
		model.addAttribute("ListReport", list);
		return "adminoverview";
	}
	
	@ModelAttribute("totalOrders")
	private Integer getTotalOrders() {
		return ordersServiceImpl.getTotalOrders();
	}
	
	@ModelAttribute("totalNewUser")
	private Integer getTotalNewUsers() {
		return usersServiceImpl.getToTalNewUser();
	}
	
	@ModelAttribute("totalQuantitySold")
	private Integer getTotalQuantitySold() {
		return productServiceImpl.getTotalQuantitySold();
	}
	
	@ModelAttribute("listOrders")
	private List<Object[]> getListOrders(){
		return ordersServiceImpl.findListOrders();
	}

}
