package com.TechGhuru.SivajiAndSons.dto;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class customMail {

	
	private List<String> toaddress;
	private String subject;
	private String content;
	

	public List<String> getToaddress() {
		return toaddress;
	}
	public void setToaddress(List<String> toaddress) {
		this.toaddress = toaddress;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
