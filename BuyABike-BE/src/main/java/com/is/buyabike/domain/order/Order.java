package com.is.buyabike.domain.order;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.stereotype.Component;

import com.is.buyabike.domain.Product;

@Component
public class Order {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@OneToMany
	private List<OrderItem> items;
	
	public Order() {
		items = new ArrayList<OrderItem>();
	}
	
	public List<OrderItem> getItems() {
		return items;
	}
	
	public void addProduct(Product product) {
		for (OrderItem item : items) {
			if (item.getProduct().equals(product)) {
				item.incrementAmount();
				return;
			}
		}
		
		OrderItem item = new OrderItem(product, 1);
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
}
