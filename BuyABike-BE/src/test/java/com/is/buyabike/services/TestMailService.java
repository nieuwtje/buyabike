package com.is.buyabike.services;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.is.buyabike.mail.IMailManager;

public class TestMailService {
	@Autowired
	IMailManager mailService;
	 
	@Test
	public void testSendMail() {
		mailService.sendMail("endcasebuyabike@gmail.com","t.nieuwenhuys@hotmail.com","TEST SUBJECT","TEST BODY");
	}
}
