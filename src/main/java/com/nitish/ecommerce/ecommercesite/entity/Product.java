package com.nitish.ecommerce.ecommercesite.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class Product {
	
	public enum StockStatus{
		AVILABLE,
		OUT_OF_STOCK
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private String name;
	
	@Column(columnDefinition = "ENUM('AVILABLE', 'OUT_OF_STOCK')")
    @Enumerated(EnumType.STRING)
	private StockStatus stockStatus;

	@Column(length = 8000)
	private String imageUrl;

	@Lob
	private String description;

	
    @Column(name = "unit_price")
    private BigDecimal unitPrice;

	@ManyToOne
	@JoinColumn(name = "category_id",nullable = false)
	private ProductCategory category;
	
	public Product() {
		super();
	}

	

	public Product(long id, String name, StockStatus stockStatus, String imageUrl, String description,
			BigDecimal unitPrice, ProductCategory category) {
		this.id = id;
		this.name = name;
		this.stockStatus = stockStatus;
		this.imageUrl = imageUrl;
		this.description = description;
		this.unitPrice = unitPrice;
		this.category = category;
	}



	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public StockStatus getStockStatus() {
		return stockStatus;
	}
	public void setStockStatus(StockStatus stockStatus) {
		this.stockStatus = stockStatus;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}


	public ProductCategory getCategory() {
		return category;
	}

	public void setCategory(ProductCategory category) {
		this.category = category;
	}

}
