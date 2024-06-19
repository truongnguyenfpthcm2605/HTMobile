package com.poly.app.config;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.poly.app.enity.Users;
import com.poly.app.service.SessionService;
import com.poly.app.util.Keyword;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor{
	private final SessionService sessionService;
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String uri = request.getRequestURI();
		sessionService.setAttribute("uri", uri);
		Users user = sessionService.getAttribute(Keyword.acc);
		String error = "";
		if(user==null) {
			error = "Please Login!";
		}else if(!user.getRole() && uri.startsWith("/admin")) {
			error = "Access denied";
		}
		
		if(!error.isEmpty()) {
			sessionService.setAttribute("security-uri", uri);
			response.sendRedirect("/login?error="+error);
			return false;
		}
		return true;
	}
		
}
