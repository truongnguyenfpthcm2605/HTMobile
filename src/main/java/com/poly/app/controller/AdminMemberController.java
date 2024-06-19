package com.poly.app.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.poly.app.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.poly.app.Impl.UsersServiceImpl;
import com.poly.app.enity.Users;
import com.poly.app.util.AES;
import com.poly.app.util.ExcelUtil;
import com.poly.app.util.Keyword;
import com.poly.app.util.SortAnPage;

import jakarta.servlet.ServletContext;

@Controller
@RequestMapping("admin/member")
@RequiredArgsConstructor
public class AdminMemberController {
	private final UsersService usersServiceImpl;
	private final ServletContext app;

	@GetMapping("")
	public String index(Model model) {
		model.addAttribute("ListUser", usersServiceImpl.findAll());
		printExcel();
		return "adminmember";
	}
	
	@GetMapping("view/{id}")
	public String view(Model model ,@PathVariable("id") Integer id) {
		Users user = usersServiceImpl.findById(id).get();
		
		
		model.addAttribute("user", user);
		model.addAttribute("pass", AES.decrypt(user.getPassword(), Keyword.keyCode));
		return "viewmember";
	}
	
	@GetMapping("block/{id}")
	private String block(@PathVariable("id") Integer id) {
		Users us = usersServiceImpl.findById(id).get();
		if(us.getActive()) {
			us.setActive(false);
		}else {
			us.setActive(true);
		}
		usersServiceImpl.update(us);
		return "redirect:/admin/member";
	}
	
	@PostMapping("/update")
	private String update(
			@RequestParam("id") Integer id,
			@RequestParam("active") Boolean active,
			@RequestParam("role") Boolean role) {
		
		Users us = usersServiceImpl.findById(id).get();
		us.setActive(active);
		us.setRole(role);
		us.setUpdateday(new Date());
		usersServiceImpl.update(us);
		printExcel();
		return "redirect:/admin/member";
	}
	
	@PostMapping("search")
	private String search(Model model,
			@RequestParam("search") Optional<String> s) {
		List<Users> list = usersServiceImpl.findByKeywords("%"+s.orElse("")+"%");
		model.addAttribute("search", s.orElse(""));
		model.addAttribute("ListUser", list);
		return "adminmember";
		
	}
	
	@PostMapping("sort")
	private String sort(Model model, @RequestParam("sortUser") String sortKey
			) {
		List<Users> list = usersServiceImpl.findAll( SortAnPage.getSort(sortKey));
		model.addAttribute("key", sortKey);
		model.addAttribute("ListUser", list);
		return "adminmember";
	}
	
	@PostMapping("active")
	private String isActive(Model model, @RequestParam("acitveUser") Boolean isActive) {
		List<Users> list = usersServiceImpl.findByActive(isActive).get();
		model.addAttribute("active", isActive);
		model.addAttribute("ListUser", list);
		return "adminmember";
	}
	
	
	private  void printExcel(){
		String filePath = app.getRealPath("/document/Accounts.xlsx");
		String[] header = {"Stt","Id","Họ và Tên","Mật Khẩu","Email","Giới Tính",
		"Ngày Sinh","Trạng Thái","Ngày Đăng Ký","Ngày Cập Nhật","Quyền Truy Cập"};
		
		ExcelUtil.writeExcelUser(filePath,  usersServiceImpl.findAll(),header);
	}
}
