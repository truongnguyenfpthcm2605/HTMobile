package com.poly.app.controller;

import com.poly.app.Impl.CategoriesServiceImpl;
import com.poly.app.Impl.ProductServiceImpl;
import com.poly.app.enity.Categories;
import com.poly.app.enity.Product;
import com.poly.app.service.CategoriesService;
import com.poly.app.service.ParamService;
import com.poly.app.service.ProductService;
import com.poly.app.service.SessionService;
import com.poly.app.util.SortAnPage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("admin/product")
@RequiredArgsConstructor
public class AdminProductController {
	private final ProductService productServiceImpl;
	private final CategoriesService categoriesServiceImpl;
	private final SessionService sessionService;
	private final ParamService paramService;

	@GetMapping("")
	public String index(Model model) {
		model.addAttribute("ListProduct", productServiceImpl.findAll());
		return "adminproduct";
	}

	@GetMapping("add")
	public String add(Model model) {
		sessionService.setAttribute("action", "add");
		sessionService.removeAttribute("p");
		model.addAttribute("p", new Product());
		return "addproduct";
	}

	@GetMapping("view/{id}")
	public String view(@PathVariable("id") Integer id, Model model) {
		Product product = productServiceImpl.findById(id).get();
		sessionService.setAttribute("action", "update");
		sessionService.setAttribute("p", product);
		model.addAttribute("p", product);
		return "addproduct";
	}

	@PostMapping("search")
	private String search(Model model, @RequestParam("search") Optional<String> keyword) {
		List<Product> list = productServiceImpl.findByKeywordsSearch("%" + keyword.orElse("") + "%");
		model.addAttribute("search", keyword.orElse(""));
		model.addAttribute("ListProduct", list);
		return "adminproduct";
	}

	@PostMapping("month")
	private String getMonth(Model model, @RequestParam("months") Integer month) {
		model.addAttribute("ListProduct", productServiceImpl.findByProductMonth(month).get());
		model.addAttribute("month", month);
		return "adminproduct";
	}

	@PostMapping("sort")
	private String sort(Model model, @RequestParam("sortProduct") String sortKey) {
		List<Product> list = productServiceImpl.findAll(SortAnPage.getSort(sortKey));
		model.addAttribute("key", sortKey);
		model.addAttribute("ListProduct", list);
		return "adminproduct";
	}

	@PostMapping("category")
	private String category(Model model, @RequestParam("category") Integer categoryId) {
		List<Product> list = productServiceImpl.findBycategory(categoryId);
		model.addAttribute("cate", categoryId);
		model.addAttribute("ListProduct", list);
		return "adminproduct";
	}

	@PostMapping("/update")
	private String update(Model model,
			@RequestParam("title") String title,
			@RequestParam("pricecost") Double pricecost,
			@RequestParam("price") Double price,
			@RequestParam("quanityfinal") Integer quanityfinal,
			@RequestParam("chip") String Chip,
			@RequestParam("description") String description,
			@RequestParam("category") Integer category,
			@RequestParam("ram") Integer ram,
			@RequestParam("rom") Integer rom,
			@RequestParam("pin") Integer pin,
			@RequestParam("camera") Integer camera,
			@RequestParam("screen") Double screen,
			@RequestParam("imgproduct") MultipartFile fileimg,
			@RequestParam("thumnail") MultipartFile[] thumnail,
			@RequestParam("id") Optional<Integer> id) {

		Categories categories = categoriesServiceImpl.findById(category).get();

		File main = null;
		String files = "";
		Product product =null;
		try {
			String action =  sessionService.getAttribute("action");
			main = paramService.save(fileimg, "/img/products");
			files = paramService.getStringfiles(thumnail, "/img/detailproduct");
			try {
				product = productServiceImpl.findById(id.orElse(0)).get();
			} catch (Exception e) {
				product =null;
			}
			Product pro = convertToProduct(product, title, pricecost, price, quanityfinal, Chip, description,
					categories, ram, rom, pin, camera, screen, main.getName(), files);
			if (action.equals("update")) {
				pro.setUpdateday(new Date());
				productServiceImpl.update(pro);

			} else {
				pro.setCreateday(new Date());
				pro.setUpdateday(new Date());
				pro.setQuantitysold(0);
				productServiceImpl.save(pro);

			}
			pro = new Product();
			return "redirect:/admin/product";
		} catch (Exception e) {
			Product pro = convertToProduct(product, title, pricecost, price, quanityfinal, Chip, description, categories,
					ram, rom, pin, camera, screen, "", "");
			e.printStackTrace();
			System.out.println("Lỗi");
			model.addAttribute("p", pro);
			model.addAttribute("messageFile", "Không được bỏ trống ảnh chỉnh, và ảnh mô tả");
			return "addproduct";
		}

	}

	@ModelAttribute("listCategory")
	private List<Categories> getCategories() {
		List<Categories> list = categoriesServiceImpl.findAll();
		return list;
	}

	@ModelAttribute("listRam")
	private List<Integer> getListRam() {
		List<Integer> list = Arrays.asList(2, 4, 6, 8, 10, 12, 16);
		return list;
	}

	@ModelAttribute("listRom")
	private List<Integer> getListRom() {
		List<Integer> list = Arrays.asList(16, 32, 64, 128, 256, 512);
		return list;
	}

	private Product convertToProduct(Product productOld, String title, Double pricecost, Double price, Integer quantity,
			String chip, String description, Categories categories, Integer ram, Integer rom, Integer pin,
			Integer camera, Double screen, String filename, String thumnail) {
		if (productOld != null) {
			productOld.setTitle(title);
			productOld.setPrice(price);
			productOld.setPricecost(pricecost);
			productOld.setCamera(camera);
			productOld.setChip(chip);
			productOld.setDescription(description);
			productOld.setImgproduct(filename);
			productOld.setCategory(categories);
			productOld.setScreen(screen);
			productOld.setQuanityfinal(quantity);
			productOld.setRam(ram);
			productOld.setRom(rom);
			productOld.setPin(pin);
			productOld.setThumnail(productOld.getThumnail());
			
			return productOld;
		}
		Product product = new Product();
		product.setTitle(title);
		product.setPrice(price);
		product.setPricecost(pricecost);
		product.setCamera(camera);
		product.setChip(chip);
		product.setDescription(description);
		product.setImgproduct(filename);
		product.setCategory(categories);
		product.setThumnail(thumnail);
		product.setScreen(screen);
		product.setQuanityfinal(quantity);
		product.setRam(ram);
		product.setRom(rom);
		product.setPin(pin);
		product.setViews(1L);
		return product;
	}
}
