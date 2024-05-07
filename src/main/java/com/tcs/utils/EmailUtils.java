package com.tcs.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Component
public class EmailUtils {
	
	 @Autowired
	    private JavaMailSender mailSender;

	    public void sendEmail(String to, String subject, String body) {
	    	MimeMessage message = mailSender.createMimeMessage();
	        MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");

	        try {
	            helper.setTo(to);
	            helper.setSubject(subject);
	            helper.setText(body, true); 
	            mailSender.send(message);
	        } catch (MessagingException e) {
	            e.printStackTrace(); 
	        }
	    }
}
