package com.is.buyabike.domain.order;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.is.buyabike.domain.Product;
import com.is.buyabike.validation.NonNegativeNumber;

@Entity
@XmlRootElement(name = "item")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderItem {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@OneToOne
	private Product product;
	
	@NonNegativeNumber
	private int amount;
	
	public OrderItem() {
		
	}
	
	public OrderItem(Product product, int amount) {
		this.product = product;
		this.amount = amount;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getAmount() {
		return amount;
	}
	
	public void incrementAmount() {
		amount++;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
