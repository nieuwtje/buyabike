package com.is.buyabike.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.is.buyabike.domain.client.Client;

@Transactional
@Repository
public class ClientDao {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Transactional(readOnly=true)
	public Client findWithId(long id) {
		return entityManager.find(Client.class, id);
	}

	@Transactional
	public void persist(Client client){
		entityManager.persist(client);
	}

	@Transactional(readOnly=true)
	public List<Client> findAll(){
		TypedQuery<Client> q = entityManager.createQuery("SELECT p FROM Client p", Client.class);
		return q.getResultList();
	}

	@Transactional
	public void delete(Client client){
		Client clientToRemove = this.findWithId(client.getId());
		entityManager.remove(clientToRemove);
	}

	@Transactional
	public Client update(Client client){
		return entityManager.merge(client);
	}


}
