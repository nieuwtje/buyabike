package com.is.buyabike.mail;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

@Component
public class MailSender extends JavaMailSenderImpl {
	@Override
	public String getHost() {
		return "smtp.gmail.com";
	}
	
	@Override 
	public int getPort() {
		return 587;
	}
	
	@Override 
	public String getPassword() {
		return "buyabike";
	}
	
	@Override
	public String getUsername() {
		return "endcasebuyabike@gmail.com";
	}
}
