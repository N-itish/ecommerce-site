package com.nitish.ecommerce.ecommercesite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.nitish.ecommerce.ecommercesite.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Long>{    
    Customer findByName(String name);
}
