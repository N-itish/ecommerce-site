package com.nitish.ecommerce.ecommercesite.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nitish.ecommerce.ecommercesite.entity.Product;

public interface ProductRepository extends JpaRepository<Product,Long>{
    Product findByName(String productName);
}
