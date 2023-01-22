package com.nitish.ecommerce.ecommercesite.service;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nitish.ecommerce.ecommercesite.dto.ProductForm;
import com.nitish.ecommerce.ecommercesite.entity.Product;
import com.nitish.ecommerce.ecommercesite.entity.ProductCategory;
import com.nitish.ecommerce.ecommercesite.repository.ProductCategoryRepository;
import com.nitish.ecommerce.ecommercesite.repository.ProductRepository;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ProductRepository productsRepo;

    @Autowired
    private ProductCategoryRepository productCatRepo;

    

    public boolean addProduct(ProductForm productForm) {
        Product product = mapper.map(productForm, Product.class);
        //getting the product category selected by the user using the id
        ProductCategory pc = productForm.getCategories().stream().filter(
            c-> c.getId() == productForm.getCategoryId()
        ).findAny().orElse(null);
        product.setCategory(pc);
        //to prevent model mapper from mapping id --> catergoryId
        product.setId(-1);
       
        boolean inserted = false;
        try {
            productsRepo.save(product);
            inserted = true;
        }catch(Exception exc){
            inserted = false;
        }
        return inserted;
    } 

    public List<Product> findAll(){
        return productsRepo.findAll();
    }

    public List<ProductCategory> getCategories(){
        return productCatRepo.findAll();
    }

    public ProductForm getProduct(ProductForm product){
        ProductForm newProduct;
		if(product.getName() == null){
			newProduct = new ProductForm();
		}else{
			newProduct = product;
		}
        newProduct.setCategories(productCatRepo.findAll());
        return newProduct;
    }
}
