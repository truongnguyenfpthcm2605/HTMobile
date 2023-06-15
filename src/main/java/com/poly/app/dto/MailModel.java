package com.poly.app.dto;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MailModel {
	
	private String form = "Hello";
	private String to;
	private String subject;
	private String body;
	String[] cc ;
	String[] bcc;
	List<File> files = new ArrayList<>();
	
	public MailModel(String to, String subject, String body) {
		super();
		this.to = to;
		this.subject = subject;
		this.body = body;
	}
	
	
}
