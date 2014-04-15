package com.is.buyabike.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import com.is.buyabike.mail.IMailManager;

@Service
public class MailService implements IMailManager {
	@Autowired
	private JavaMailSenderImpl mailSender;
 
	public void sendMail(String from, String to, String subject, String msg) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(from);
		message.setTo(to);
		message.setSubject(subject);
		message.setText(msg);

		mailSender.send(message);	
	}
	
	public void sendPreConfiguredMail(String to, SimpleMailMessage configuredMessage) {
	    SimpleMailMessage mailMessage = new SimpleMailMessage(configuredMessage);
	    mailMessage.setTo(to);
	    mailSender.send(mailMessage);
	}
}
