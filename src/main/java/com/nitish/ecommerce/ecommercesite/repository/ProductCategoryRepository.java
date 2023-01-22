package com.nitish.ecommerce.ecommercesite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

import com.nitish.ecommerce.ecommercesite.entity.ProductCategory;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Long>{    
    @Query("select c.categoryName from ProductCategory c")
    public List<String> getCategoryNames();

    public ProductCategory findByCategoryName(String categoryName);
}
