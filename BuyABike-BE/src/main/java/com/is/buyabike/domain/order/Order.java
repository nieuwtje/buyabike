package com.is.buyabike.domain.order;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.stereotype.Component;

import com.is.buyabike.domain.Product;

@Component
@Entity
@XmlRootElement(name = "order")
@XmlAccessorType(XmlAccessType.FIELD)
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@OneToMany
	@XmlElementWrapper(name = "items")
	@XmlElementRefs({
        @XmlElementRef(name = "item", type = OrderItem.class)
	})
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
}
