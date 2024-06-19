package com.poly.app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
public class SessionService {

	private final HttpSession session;
	public void setAttribute(String name,Object value) {
		session.setAttribute(name, value);
	}
	public <T> T getAttribute(String name) {
		return (T) session.getAttribute(name);
	}
	public void removeAttribute(String name) {
		session.removeAttribute(name);
	}
}
