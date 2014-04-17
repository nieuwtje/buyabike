package com.is.buyabike.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.is.buyabike.dao.ClientDao;
import com.is.buyabike.domain.client.Client;

@Service
public class ClientService {

	@Autowired
	private ClientDao clientDao;
	
	public void create(Client client) {
		clientDao.persist(client);
	}
}
