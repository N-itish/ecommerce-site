package com.nitish.ecommerce.ecommercesite.controller;


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
		System.out.println("MyController : registerUser username="+user.getName());
		if(result.hasErrors()){
			return "register-form";
		}
		userService.saveUser(user);
		return "redirect:/login-form";
	}
}

