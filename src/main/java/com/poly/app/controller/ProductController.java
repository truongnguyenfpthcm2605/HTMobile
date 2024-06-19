package com.poly.app.controller;

import java.util.List;
import java.util.Optional;

import com.poly.app.service.ProductService;
import com.poly.app.service.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.app.Impl.ShoppingCartServiceImpl;
import com.poly.app.enity.Product;
import com.poly.app.service.SessionService;
import com.poly.app.util.Keyword;

@Controller
@RequestMapping("product")
@RequiredArgsConstructor
public class ProductController {
	public static final Integer PAGE_SIZE = 9;
	private final ShoppingCartService shoppingCartServiceImpl;
	private final SessionService sessionService;
	private final ProductService productService;

	@ModelAttribute("numberCartItem")
	public int getnumberCartItem() {
		return shoppingCartServiceImpl.getCount() != 0 ? shoppingCartServiceImpl.getCount() : 0;
	}

	@GetMapping("views")
	public String getAllProduct(Model model) {
		model.addAttribute("pageProduct", getPage(0));
		return "allproduct";
	}
	
	@GetMapping("page")
	public String Page(Model model,@RequestParam("p") Optional<Integer> p) {
		String keyw = sessionService.getAttribute(Keyword.search);
		if(keyw!=null) {
			Pageable pageable = PageRequest.of(p.orElse(0), PAGE_SIZE);
			Page<Product> page = productService.findByKeywords("%"+keyw+"%", pageable);
			model.addAttribute("pageProduct", page);
			
		}else {
			model.addAttribute("pageProduct", getPage(p.orElse(0)));
		}
		return "allproduct";
	}
	
	@GetMapping("detail")
	public String detail(Model model , @RequestParam("id") Optional<Integer> id) {
		Product product = productService.findById(id.orElse(1)).get();
		product.setViews(product.getViews()+1);
		String[] thumnail = product.getThumnail().split("-");
		List<Product> productRecomend = productService.findProductsByCategoryId(product.getCategory().getId(), 4);
		if(productRecomend!=null) {
			model.addAttribute("pc", productRecomend);
		}
		productService.update(product);
		model.addAttribute("p", product);
		model.addAttribute("images", thumnail);
		return "detailproduct";
	}
	
	@PostMapping("search")
	private String search(Model model,@RequestParam("search") Optional<String> keyword) {
		Pageable pageable = PageRequest.of(0, PAGE_SIZE);
		sessionService.setAttribute(Keyword.search, keyword.orElse(""));
		Page<Product> page = productService.findByKeywords("%"+keyword.orElse("")+"%", pageable);
		model.addAttribute("pageProduct", page);
		model.addAttribute("keywords", keyword.orElse(""));
		return "allproduct";
	}
	
	
	@PostMapping("sort")
	private String sort(Model model ,@RequestParam("sort") Integer sort) {
		int check = sort;
		Page<Product> page =null;
		switch (sort) {
		case 0:
			Sort sorts = Sort.by(Direction.DESC, "createday");
			page = getPage(0, sorts);
			break;
		case 1:
			Sort sortMax = Sort.by(Direction.DESC, "price");
			page = getPage(0, sortMax);
			break;
		case 2:
			Sort sortsMin = Sort.by(Direction.ASC, "price");
			page = getPage(0, sortsMin);
			break;
		default:
			break;
		}
		
		model.addAttribute("pageProduct", page);
		model.addAttribute("check", check);
		return "allproduct";
	}
	
	public Page<Product> getPage(Integer number){
		Pageable pageable = PageRequest.of(number, PAGE_SIZE);
		Page<Product> page = productService.findAll(pageable);
		return page;
	}
	
	
	public Page<Product> getPage(Integer number,Sort sort){
		Pageable pageable = PageRequest.of(number, PAGE_SIZE,sort);
		Page<Product> page = productService.findAll(pageable);
		return page;
	}
}
