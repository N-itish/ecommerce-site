package com.nitish.ecommerce.ecommercesite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
		RedirectAttributes attributes,@RequestParam(name = "status",required= false) Boolean insertStatus,
		@ModelAttribute("productForm") ProductForm productForm
		) {
		ProductForm form = productService.getProduct(productForm);
		attributes.addFlashAttribute("productForm",form);
		attributes.addAttribute("insertStatus", insertStatus);
		return "redirect:/admin/dashboard";
	}
	

	@PostMapping("/add-product")
	public String addProduct(RedirectAttributes attributes,ProductForm productForm){
		boolean insertStatus = productService.addProduct(productForm);
		attributes.addFlashAttribute("productForm", productForm);
		attributes.addAttribute("insertStatus", insertStatus);
		return "redirect:/admin/dashboard";
	}

	
}
