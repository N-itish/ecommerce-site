package com.nitish.ecommerce.ecommercesite.controller;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nitish.ecommerce.ecommercesite.dto.UserProfile;
import com.nitish.ecommerce.ecommercesite.service.ProductService;
import com.nitish.ecommerce.ecommercesite.service.UserService;
import com.nitish.ecommerce.ecommercesite.entity.OrderItem;

@Controller
public class MyController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping({"/","/{orderTrackingNo}"})
	public String index(Model model,@RequestParam(name = "orderTrackingNo",required= false) String orderTrackingNo) {
		if(orderTrackingNo != null){
			System.out.println(orderTrackingNo);
		}

		model.addAttribute("categories", productService.getCategories());
		model.addAttribute("orderTrackingNo", orderTrackingNo);
		return "index";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login-form";
	}
	

	@GetMapping("/register")
	public String getRegisterForm(Model model){
		model.addAttribute("userProfile", new UserProfile());		
		return "register-form";
	}

	@PostMapping("/register")
	public String registerUser(@Valid UserProfile user,BindingResult result,Model model){
		if(result.hasErrors()){
			System.out.println("Register has errors");
			return "register-form";
		}
		userService.saveUser(user);
		return "redirect:/login-form";
	}

	//testing random function
	@GetMapping("/test")
	public String test(Model model){
		List<OrderItem> myList= new ArrayList<OrderItem>();
		OrderItem newItem = new OrderItem();
		newItem.setName("test");
		newItem.setId(1);
		newItem.setQuantity(BigDecimal.valueOf(23));
		myList.add(newItem);
		model.addAttribute("test", newItem);
		return "test";
	}
}

