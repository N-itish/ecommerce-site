package com.nitish.ecommerce.ecommercesite.dto;

import java.math.BigDecimal;
import java.util.Set;
import com.nitish.ecommerce.ecommercesite.entity.OrderItem;

public class PurchaseOrder {
    private int totalQuantity;
    private BigDecimal totalPrice;
    private Set<OrderItem> orderItems;
    public PurchaseOrder(){

    }
    
    
    public PurchaseOrder(int totalQuantity, BigDecimal totalPrice,  Set<OrderItem> orderItems) {
        this.totalQuantity = totalQuantity;
        this.totalPrice = totalPrice;
        this.orderItems = orderItems;
    }


    public int getTotalQuantity() {
        return totalQuantity;
    }


    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }


    public BigDecimal getTotalPrice() {
        return totalPrice;
    }


    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }



    public Set<OrderItem> getOrderItems() {
        return orderItems;
    }


    public void setOrderItems(Set<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }  
}
