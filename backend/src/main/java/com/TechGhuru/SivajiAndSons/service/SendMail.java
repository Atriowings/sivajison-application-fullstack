package com.TechGhuru.SivajiAndSons.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class SendMail {

	@Autowired
	private JavaMailSender javamailsender;
	
	public void sendhtmlmail(String to,String subject,String html) throws MessagingException {
		
		MimeMessage message = javamailsender.createMimeMessage();
		
		MimeMessageHelper helper = new MimeMessageHelper(message,true);
		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText(html,true);
		
		javamailsender.send(message);
	}
}
