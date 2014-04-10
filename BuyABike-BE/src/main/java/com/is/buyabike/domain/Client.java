package com.is.buyabike.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Client {
	
	@Id
	@GeneratedValue
	private long id;
	
	private String firstName;
	private String lastName;
	
	private Address address;
	
	private String password;
	

}
