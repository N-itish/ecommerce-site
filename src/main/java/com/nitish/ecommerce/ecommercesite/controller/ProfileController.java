package com.nitish.ecommerce.ecommercesite.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.nitish.ecommerce.ecommercesite.entity.Customer;
import com.nitish.ecommerce.ecommercesite.entity.Order;
import com.nitish.ecommerce.ecommercesite.service.OrderService;
import com.nitish.ecommerce.ecommercesite.service.UserService;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;
    
    @GetMapping({"/dashboard","/dashboard/{orderTrackingNo}"})
	public String getProfileForm(
            @PathVariable(value = "orderTrackingNo", required = false) String orderTrackingNo,
            @ModelAttribute(name = "customer") Customer customer,
            Model model
        ){
		model.addAttribute("customer", customer);
        model.addAttribute("orderTrackingNo", orderTrackingNo);
		return "profile-form";
	}

	@GetMapping("/user/{userName}")
	public String getProfileFragment(@PathVariable String userName,RedirectAttributes attr, Model model){
		Customer customer =  userService.getUserByUserName(userName);
		attr.addFlashAttribute("customer", customer);
		return "redirect:/profile/dashboard";
	}
    
    @GetMapping("/order/{orderTrackingNo}/user/{userName}")
    public String getOrdersFragment(
        @PathVariable("userName") String userName,
        @PathVariable("orderTrackingNo") String orderTrackingNo,
        RedirectAttributes attr
    ){
        Set<Order> orders = orderService.getPurchaseOrder(userName);
        attr.addFlashAttribute("Orders",orders);
        return "redirect:/profile/dashboard/{orderTrackingNo}";
    }

    @GetMapping("/order/{orderTrackingNo}/orderItems/user/{username}/prev/{prevOrderTrackingNo}")
    public String getOrdersItemsFragment(@PathVariable("username") String userName,
    @PathVariable("orderTrackingNo") String orderTrackingNo,
    @PathVariable("orderTrackingNo") String prevOrderTrackingNo,
    RedirectAttributes attribute){
        Order order  = orderService.getOrderByUserName(userName,orderTrackingNo);
        attribute.addFlashAttribute("Order",order);
        return "redirect:/profile/dashboard/{prevOrderTrackingNo}";
    }
}
