package com.is.buyabike.domain.order;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.is.buyabike.domain.Product;
import com.is.buyabike.domain.client.Client;

@Entity
@Table(name = "clientorder")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Order {
	public enum OrderStatus {
		RECEIVED,
		ONROUTE,
		DELIVERED,
		CANCELED
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@OneToMany(cascade = {CascadeType.ALL})
	private List<OrderItem> items = new ArrayList<OrderItem>();
	
	@Enumerated(EnumType.ORDINAL)
	private OrderStatus status = OrderStatus.RECEIVED;
	
	@ManyToOne
	@JsonIgnore(value = true)
	private Client client;
	
	public Order() {
		super();
	}
	
	public Order(Client client) {
		super();
		this.client = client;
		client.addOrder(this);
	}
	
	public List<OrderItem> getItems() {
		return items;
	}
	
	public void setItems(List<OrderItem> items) {
		this.items = items;
	}
	
	public void addProduct(Product product) {
		for (OrderItem item : items) {
			if (item.getProduct().equals(product)) {
				item.incrementAmount();
				return;
			}
		}
		
		System.out.println("Added product " + product.getDescription());
		addOrderItem(new OrderItem(product, 1));
	}
	
	public void addOrderItem(OrderItem item) {
		items.add(item);
	}
	
	public void removeProduct(Product product) {
		for (OrderItem item : items) {
			if (item.getProduct().equals(product)) {
				items.remove(item);
				return;
			}
		}
	}
	
	public void changeAmountOfProduct(Product product, int amount) {
		for (OrderItem item : items) {
			if (item.getProduct().equals(product)) {
				item.setAmount(amount);
				return;
			}
		}
	}
	
	public OrderItem getOrderItemProductById(long id) {
		for (OrderItem item : items) {
			if (item.getProduct().getId() == id) {
				return item;
			}
		}
		
		return null;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
}
