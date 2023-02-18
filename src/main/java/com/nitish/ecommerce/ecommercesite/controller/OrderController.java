package com.nitish.ecommerce.ecommercesite.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nitish.ecommerce.ecommercesite.configuration.MyPrincipal;
import com.nitish.ecommerce.ecommercesite.dto.ProductOrder;
import com.nitish.ecommerce.ecommercesite.entity.OrderItem;
import com.nitish.ecommerce.ecommercesite.service.OrderService;

import com.nitish.ecommerce.ecommercesite.entity.Order;


@Controller
@RequestMapping(value = "/order",method = RequestMethod.GET)
public class OrderController {
    @Autowired
    private ModelMapper mapper;
    
    @Autowired
    private OrderService orderService;
    @PostMapping({"/order-form/{product_name}","/order-form/{productName}/orderId/{orderTrackingNo}"})
	public String getProductsOrderForm(
        @AuthenticationPrincipal MyPrincipal principal,
        @RequestParam(name = "status",required= false) boolean insertStatus,
        @PathVariable("productName") String productName,Model model,
        @PathVariable(value = "orderTrackingNo",required = false) String orderTrackingNo
     ){
		ProductOrder calledProduct = orderService.getProductOrder(productName);
        Order order = orderService.getOrderByUserName(principal.getUsername(), orderTrackingNo);
        if(orderTrackingNo == null){
            orderTrackingNo = order.getOrderTrackingNumber();
        }
        model.addAttribute("orderPurchaseStatus", order.isOrderPurchased());
		model.addAttribute("productOrder", calledProduct);
        model.addAttribute("orderTrackingNo", orderTrackingNo);
		return "product-desc";
	}
    

    @PostMapping({"/user/{userName}/add-item","/user/{userName}/add-item/{orderTrackingNo}"}) public String addOrder
    (
        @PathVariable("userName") String username,
        @PathVariable("orderTrackingNo") String orderTrackingNo,
        ProductOrder productOrder,
        RedirectAttributes attributes
     ){
        OrderItem item = mapper.map(productOrder, OrderItem.class);
        boolean insertedStatus = false;
        String ordTrcNo = null;
        try {
            ordTrcNo = this.orderService.addItemNew(username,orderTrackingNo,item);
            insertedStatus = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        attributes.addAttribute("orderTrackingNo",ordTrcNo);
        attributes.addAttribute("insertStatus",insertedStatus);
        //redirecting the user to the main page after inserting item
        return "redirect:/admin/dashboard";

    }

    @PostMapping("/purchase/user/{userName}/orderId/{orderTrackingNumber}")
    public String purchaseItem(
    @PathVariable("userName") String userName, 
    @PathVariable("orderTrackingNumber") String orderTrackingNo){
       orderService.savePurchase(userName, orderTrackingNo);

       //redirecting the user to the main page after purchase 
       return "redirect:/?orderTrackingNo=null";
    }

    @GetMapping("/cancel/user/{username}/{orderTrackingNo}")
    public String canceOrder(
        @PathVariable("username") String username,
        @PathVariable("orderTrackingNo") String orderTrackingNo,
        RedirectAttributes attributes){
            attributes.addFlashAttribute("User_Orders",
            orderService.cancelOrder(username,orderTrackingNo));

        return "redirect:/admin/dashboard";
    }
}
