package com.nitish.ecommerce.ecommercesite.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nitish.ecommerce.ecommercesite.entity.Order;

public interface OrderRepository extends JpaRepository<Order,Long>{
}
