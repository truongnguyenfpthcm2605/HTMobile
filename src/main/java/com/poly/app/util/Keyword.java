package com.poly.app.util;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class Keyword {
	public static final String keyCode = "Mainkey";
	public static final String acc = "account";
	public static final String voucher = "keyVoucher";
	public static final String search = "keySearch";
	public static String keyCodeRandom() {
		Random ran = new Random();
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < 6; i++) {
			str.append(ran.nextInt(10));
		}
		return str.toString();
	}
}
