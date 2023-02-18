package com.nitish.ecommerce.ecommercesite.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String stockStatus;
    private long noOfStock;


    @OneToOne(mappedBy="stockStatus")
    private Product product;
    public Stock(){
        super();
    }

    public Stock(String stockStatus, long noOfStock) {
        this.stockStatus = stockStatus;
        this.noOfStock = noOfStock;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getStockStatus() {
        return stockStatus;
    }
    public void setStockStatus(String stockStatus) {
        this.stockStatus = stockStatus;
    }
    public long getNoOfStock() {
        return noOfStock;
    }
    public void setNoOfStock(long noOfStock) {
        this.noOfStock = noOfStock;
    }
    
}