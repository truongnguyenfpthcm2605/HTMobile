package com.poly.app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.poly.app.service.VoucherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.app.Impl.VoucherServiceImpl;
import com.poly.app.enity.Voucher;
import com.poly.app.service.SessionService;
import com.poly.app.util.SortAnPage;

import jakarta.validation.Valid;

@Controller
@RequestMapping("admin/gift")
@RequiredArgsConstructor
public class AdminGiftController {
	private final VoucherService voucherServiceImpl;
	private final SessionService sessionService;
	@GetMapping("")
	public String index(Model model) {
		model.addAttribute("listGift", voucherServiceImpl.findAll());
		return "admingift";
	}

	@GetMapping("add")
	public String add(Model model) {
		Voucher voucher = new Voucher();
		sessionService.setAttribute("action", "add");
		model.addAttribute("voucher", voucher);
		sessionService.removeAttribute("v");
		return "addgift";
	}

	@GetMapping("view/{id}")
	public String view(@PathVariable("id") String id, Model model) {
		Voucher voucher = voucherServiceImpl.findById(id).get();
		model.addAttribute("voucher", voucher);
		sessionService.setAttribute("v", voucher);
		sessionService.setAttribute("action", "update");
		return "addgift";
	}

	@GetMapping("delete/{id}")
	public String delete(@PathVariable("id") String id) {
		voucherServiceImpl.deleteById(id);
		return "redirect:/admin/gift";
	}

	@GetMapping("block/{id}")
	public String block(@PathVariable("id") String id) {
		Voucher voucher = voucherServiceImpl.findById(id).get();
		if (voucher.getActive()) {
			voucher.setActive(false);
		} else {
			voucher.setActive(true);
		}
		voucherServiceImpl.update(voucher);
		return "redirect:/admin/gift";
	}

	@PostMapping("search")
	private String search(Model model, @RequestParam("search") Optional<String> keyword) {
		List<Voucher> list = voucherServiceImpl.findByNameLike("%" + keyword.orElse("") + "%").get();
		model.addAttribute("listGift", list);
		model.addAttribute("search", keyword.orElse(""));
		return "admingift";
	}

	@PostMapping("sort")
	private String sort(Model model, @RequestParam("sort") String sortKey) {
		List<Voucher> list = voucherServiceImpl.findAll(SortAnPage.getSort(sortKey));
		model.addAttribute("key", sortKey);
		model.addAttribute("listGift", list);
		return "admingift";
	}

	@PostMapping("active")
	private String active(Model model, @RequestParam("active") Boolean isActive) {
		List<Voucher> list = new ArrayList<>();
		try {
			list = voucherServiceImpl.findByActive(isActive).get();
		} catch (Exception e) {
			// TODO: handle exception
		}
		model.addAttribute("active", isActive);
		model.addAttribute("listGift", list);
		return "admingift";
	}

	@PostMapping("update")
	private String update(@Valid @ModelAttribute("voucher") Optional<Voucher> voucher, BindingResult result,
			Model model) {

		Voucher voucherNew = voucher.get();
		String action = (String) sessionService.getAttribute("action");
		if (result.hasErrors()) {
			model.addAttribute("message", "Nhập đúng thông tin");
		} else {
			if (action.equals("update")) {
				voucherServiceImpl.update(voucherNew);
			} else {
				if (voucherServiceImpl.findById(voucherNew.getId()).isPresent()) {
					model.addAttribute("message", "Mã giảm giá đã tồn tại");
					return "addgift";
				}
				voucherServiceImpl.save(voucherNew);
			}
			return "redirect:/admin/gift";
		}
		return "addgift";
	}

}
