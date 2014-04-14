package com.is.buyabike.mail;

import java.util.Properties;

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
	
	@Override
	public Properties getJavaMailProperties() {
		Properties props = new Properties();
		props.setProperty("mail.smtp.auth", "true");
		props.setProperty("mail.smtp.starttls.enable", "true");
		props.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");
		
		return props;
	}
}
