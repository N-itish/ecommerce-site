package com.nitish.ecommerce.ecommercesite.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nitish.ecommerce.ecommercesite.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long>{
}
