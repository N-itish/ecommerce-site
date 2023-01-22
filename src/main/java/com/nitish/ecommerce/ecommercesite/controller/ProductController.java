package com.nitish.ecommerce.ecommercesite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.nitish.ecommerce.ecommercesite.dto.ProductForm;
import com.nitish.ecommerce.ecommercesite.service.ProductService;

@Controller
@RequestMapping(value = "/product", method = RequestMethod.GET)
public class ProductController {
    
    @Autowired
    private ProductService productService;

	@GetMapping("/product-form")
	public String getProductForm(
		Model model,@RequestParam(name = "status",required= false) Boolean insertStatus,
		@ModelAttribute("product") ProductForm productForm
		) {
		ProductForm form = productService.getProduct(productForm);
		model.addAttribute("productForm",form);
		model.addAttribute("insertStatus", insertStatus);
		return "product-form";
	}
	

	@PostMapping("/add-product")
	public String addProduct(RedirectAttributes attributes,ProductForm productForm){
		boolean insertStatus = productService.addProduct(productForm);
		attributes.addFlashAttribute("product", productForm);
		attributes.addAttribute("status", Boolean.valueOf(insertStatus));
		return "redirect:/product/product-form";
	}

	
}
