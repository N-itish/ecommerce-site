package com.nitish.ecommerce.ecommercesite.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String orderTrackingNumber;
    private int totalQuantity;
    private BigDecimal totalPrice;
    private boolean orderPurchased;

    @ManyToOne
    @JoinColumn(name = "customer_id",nullable=false)
    private Customer customer;

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL, mappedBy="order")
    private Set<OrderItem> orderItems;

    public Order() {
    }

    public Order(long id, String orderTrackingNumber, int totalQuantity, BigDecimal totalPrice,boolean orderPurchased,
     Customer customer, Set<OrderItem> orderItems) {
        this.id = id;
        this.orderTrackingNumber = orderTrackingNumber;
        this.totalQuantity = totalQuantity;
        this.totalPrice = totalPrice;
        this.customer = customer;
        this.orderItems = orderItems;
        this.orderPurchased = orderPurchased;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOrderTrackingNumber() {
        return orderTrackingNumber;
    }

    public void setOrderTrackingNumber(String orderTrackingNumber) {
        this.orderTrackingNumber = orderTrackingNumber;
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Set<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Set<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

   
     
    public void addOrderItem(OrderItem orderItem){
        if(orderItem != null){  
            if(orderItems == null){
                orderItems = new HashSet<OrderItem>();
            }
            orderItems.add(orderItem);
            orderItem.setOrder(this);
        }
    }

    public boolean isOrderPurchased() {
        return orderPurchased;
    }

    public void setOrderPurchased(boolean orderPurchased) {
        this.orderPurchased = orderPurchased;
    }    
}
