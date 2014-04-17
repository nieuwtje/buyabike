package com.is.buyabike.domain.client;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Required;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.is.buyabike.domain.Address;
import com.is.buyabike.domain.order.Order;

/**
 * 
 * Content-Type: application/json
 * {
 * 		"firstName": ..,
 * 		"lastName": ..,
 * 		"email": ..,
 * 		"password": ..,
 * 		"address": {
 * 			"street": ..,
 * 			"houseNumber": ..,
 * 			"city": ..,
 * 			"state": ..,
 * 			"country": ..
 * 		}
 * }
 * 
 */

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Client {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	public long getId() {
		return id;
	}

	@NotNull
	@NotEmpty
	private String firstName;

	@NotNull
	@NotEmpty
	private String lastName;
	
	@NotNull
	@NotEmpty
	private String password;
	
	@NotNull
	@NotEmpty
	@Column(unique = true)
	private String email;
	
	@Embedded
	private Address address;
	
	@OneToMany(mappedBy = "client")
	private List<Order> orders = new ArrayList<Order>();
	
	public Client(){
		
	}

	public Client(String firstName, String lastName, String email, Address address, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.address = address;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
	public void addOrder(Order order){
		orders.add(order);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
