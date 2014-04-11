package com.is.buyabike.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.is.buyabike.validation.NonNegativeNumber;
import com.is.buyabike.validation.NotEmpty;

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotEmpty
	private String name;

	@NotEmpty
	private String description;

	@NotEmpty
	private String imageUrl;

	@NonNegativeNumber
	private double purchasePrice;

	@NonNegativeNumber
	private double resellPrice;

	@NonNegativeNumber
	private int stock;

	private Supplier supplier;

	private List<Category> categories = new ArrayList<Category>();

	public Product(String name, String description, String imageUrl,
			double purchasePrice, double resellPrice, int stock) {
		super();
		this.name = name;
		this.description = description;
		this.imageUrl = imageUrl;
		this.purchasePrice = purchasePrice;
		this.resellPrice = resellPrice;
		this.stock = stock;
	}

	public Product() {
		super();
	}

	public double getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public double getResellPrice() {
		return resellPrice;
	}

	public void setResellPrice(double resellPrice) {
		this.resellPrice = resellPrice;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
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

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public long getId() {
		return id;
	}

	public void addCategory(Category category) {
		categories.add(category);
	}

	@Override
	public boolean equals(Object object) {
		if (object instanceof Product) {
			return getId() == ((Product) object).getId();
		}

		return false;
	}
}
