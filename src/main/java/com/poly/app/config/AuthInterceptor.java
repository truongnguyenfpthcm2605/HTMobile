package com.poly.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.poly.app.enity.Users;
import com.poly.app.service.SessionSevice;
import com.poly.app.util.Keyword;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthInterceptor implements HandlerInterceptor{
	@Autowired
	SessionSevice sessionSevice;
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String uri = request.getRequestURI();
		sessionSevice.setAttribute("uri", uri);
		Users user = sessionSevice.getAttribute(Keyword.acc);
		String error = "";
		if(user==null) {
			error = "Please Login!";
		}else if(!user.getRole() && uri.startsWith("/admin")) {
			error = "Access denied";
		}
		
		if(error.length() > 0) {
			sessionSevice.setAttribute("security-uri", uri);
			response.sendRedirect("/login?error="+error);
			return false;
		}
		return true;
	}
		
}
