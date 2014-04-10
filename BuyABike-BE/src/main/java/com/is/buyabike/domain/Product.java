package com.is.buyabike.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.is.buyabike.validation.NonNegativeNumber;
import com.is.buyabike.validation.NotEmpty;

@Entity
public class Product {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@NonNegativeNumber
	private double price;
	
	@NotEmpty
	private String name;
	
	@NotEmpty
	private String description;
	
	@NotEmpty
	private String imageUrl;

	public Product() {
		super();
	}
	
	public Product(String name, String description, String imageUrl, double price) {
		super();
		
		this.name = name;
		this.description = description;
		this.imageUrl = imageUrl;
		this.price = price;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	@Override
	public boolean equals(Object object) {
		if (object instanceof Product) {
			return getId() == ((Product) object).getId();
		}
		
		return false;
	}
}
