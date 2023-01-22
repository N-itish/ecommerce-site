package com.nitish.ecommerce.ecommercesite.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nitish.ecommerce.ecommercesite.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	User findByName(String name);
}
