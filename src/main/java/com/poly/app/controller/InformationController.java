package com.poly.app.controller;

import java.util.Optional;

import com.poly.app.service.ShoppingCartService;
import com.poly.app.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.poly.app.Impl.ShoppingCartServiceImpl;
import com.poly.app.Impl.UsersServiceImpl;
import com.poly.app.dto.ChangePass;
import com.poly.app.enity.Users;
import com.poly.app.service.SessionService;
import com.poly.app.util.AES;
import com.poly.app.util.Keyword;

import jakarta.validation.Valid;

@Controller
@RequestMapping("information")
@RequiredArgsConstructor
public class InformationController {

	private final SessionService sessionService;
	private final ShoppingCartService shoppingCartServiceImpl;
	private final UsersService usersServiceImpl;

	@ModelAttribute("numberCartItem")
	public int getnumberCartItem() {
		return shoppingCartServiceImpl.getCount() != 0 ? shoppingCartServiceImpl.getCount() : 0;
	}

	@ModelAttribute("changepass")
	private ChangePass getChangePass() {
		return new ChangePass();
	}
	
	@ModelAttribute("user")
	private Users getUsers() {
		return sessionService.getAttribute(Keyword.acc);
	}
	@GetMapping("views")
	public String infor(Model model) {
		
		return "information";
	}

	@PostMapping("changepass")
	private String changePassword(@Valid @ModelAttribute("changepass") Optional<ChangePass> change, BindingResult result,
			Model model) {
		if(result.hasErrors()) {
			model.addAttribute("message", "Vui lòng điền đầy đủ thông tin");		
		}else {
			ChangePass changePass = change.get();
			Users us = usersServiceImpl.findByEmail(getUsers().getEmail());
			if(!us.getPassword().equals(AES.encrypt(changePass.getCurrentPass(), Keyword.keyCode))) {
				model.addAttribute("message", "Mật khẩu hiện tại không đúng");
			}else if(!changePass.getNewPass().equals(changePass.getConfirmpass())) {
				model.addAttribute("message", "Xác nhận mật  không đúng");
			}else {
				us.setPassword(AES.encrypt(changePass.getNewPass(), Keyword.keyCode));
				System.out.println(AES.encrypt(changePass.getNewPass(), Keyword.keyCode));
				usersServiceImpl.update(us);
				sessionService.removeAttribute(Keyword.acc);
				return "redirect:/index";
			}
		}

		return "information";
	}

}
