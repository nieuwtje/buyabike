package com.is.buyabike.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PreRemove;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
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

	@Min(0)
	private double purchasePrice;

	@Min(0)
	private double resellPrice;

	@Min(0)
	private int stock;

	@ManyToOne
	private Supplier supplier;

	@ManyToMany
    @JoinTable(name = "product_categories",
    joinColumns =  {@JoinColumn(name = "product_id")},
    inverseJoinColumns = @JoinColumn(name = "category_id"))
	private Set<Category> categories = new HashSet<Category>();

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

	public void incrementStock(int amount) {
		this.stock = this.stock + amount;
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

	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	public void addCategory(Category category) {
		category.addProduct(this);
		this.categories.add(category);
	}

	public void removeFromCategory(Category category) {
		if(this.categories.contains(category)){

			this.categories.remove(category);
			category.removeProduct(this);
		}
	}
	@Override
	public boolean equals(Object object) {
		if (object instanceof Product) {
			return getId() == ((Product) object).getId();
		}

		return false;
	}
}
