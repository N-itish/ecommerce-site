package com.nitish.ecommerce.ecommercesite.dto;

import java.math.BigDecimal;
import java.util.List;

import com.nitish.ecommerce.ecommercesite.entity.ProductCategory;
import com.nitish.ecommerce.ecommercesite.entity.Stock;

public class ProductForm{

    private String name;
    private long categoryId;
    private Stock stock;
    private String imageUrl;
    private String description;
    private BigDecimal unitPrice;
    private List<ProductCategory> categories;

    public ProductForm() {
    }
    
    public ProductForm(String name, long categoryId,Stock stock, String imageUrl, String description, BigDecimal unitPrice,
        List<ProductCategory> categories) {
        this.name = name;
        this.stock = stock;
        this.imageUrl = imageUrl;
        this.description = description;
        this.unitPrice = unitPrice;
        this.categories = categories;
        this.categoryId = categoryId;
    }

    public Stock getStock() {
        return stock;
    }
    public void setStock(Stock stock) {
        this.stock = stock;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProductCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<ProductCategory> categories) {
        this.categories = categories;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }
}
