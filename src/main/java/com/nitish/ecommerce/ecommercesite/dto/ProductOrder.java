package com.nitish.ecommerce.ecommercesite.dto;

import java.math.BigDecimal;

import com.nitish.ecommerce.ecommercesite.entity.Stock;

public class ProductOrder {
    private String name;
    private String imageUrl;
    private String description;
    private BigDecimal unitPrice;
    private BigDecimal quantity;
    private Stock stockStatus;
    
    
    public ProductOrder() {
    }
    

    public ProductOrder(String name, String imageUrl, String description, BigDecimal unitPrice, BigDecimal quantity,
            Stock stockStatus) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.description = description;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.stockStatus = stockStatus;
        
    }

    


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }
    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }
    public BigDecimal getQuantity() {
        return quantity;
    }
    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
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


    public Stock getStockStatus() {
        return stockStatus;
    }


    public void setStockStatus(Stock stockStatus) {
        this.stockStatus = stockStatus;
    }


    @Override
    public String toString() {
        return "ProductOrder [name=" + name + ", imageUrl=" + imageUrl + ", description=" + description + ", unitPrice="
                + unitPrice + ", quantity=" + quantity + ", stockStatus=" + stockStatus + "]";
    }


    
}
