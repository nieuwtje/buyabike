package com.is.buyabike.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.is.buyabike.mail.IMailManager;
import com.is.buyabike.mail.MailSender;

@Service
public class MailService implements IMailManager {
	@Autowired
	private MailSender mailSender;
 
	public void sendMail(String from, String to, String subject, String msg) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(from);
		message.setTo(to);
		message.setSubject(subject);
		message.setText(msg);

		mailSender.send(message);	
	}
}
