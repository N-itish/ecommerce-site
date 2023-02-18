package com.nitish.ecommerce.ecommercesite.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nitish.ecommerce.ecommercesite.entity.ProductCategory;
import com.nitish.ecommerce.ecommercesite.entity.User;
import com.nitish.ecommerce.ecommercesite.service.OrderService;
import com.nitish.ecommerce.ecommercesite.service.ProductService;
import com.nitish.ecommerce.ecommercesite.service.UserService;
import java.util.List;

@Controller
@RequestMapping("/admin")    
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired 
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @GetMapping("/dashboard")
    public String dashboard(Model model,
    @RequestParam(name = "userName",required= false) String userName,
    @RequestParam(name = "orderTrackingNo",required= false) String orderTrackingNo,
    @RequestParam(name = "insertStatus",required= false) String insertStatus,
    @RequestParam(name = "categoryStatus",required= false) boolean categoryStatus
    ){
        model.addAttribute("userName",userName);
        model.addAttribute("orderTrackingNo",orderTrackingNo);
        model.addAttribute("insertStatus",insertStatus);
        model.addAttribute("categoryStatus",categoryStatus);
        return "/admin/admin-page";
    }

    @GetMapping("/user-config")
    public String userConfig(RedirectAttributes redirectAttribute){
        redirectAttribute.addFlashAttribute("Users_Data",userService.findAllUsers());
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/customer-config")
    public String getCustomers(RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("Customers", userService.findAllCustomers());
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/user-config/edit/user/{userName}")
    public String getEditFrom(@PathVariable("userName") String userName,RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("User_Data",userService.findByName(userName));
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/order-config/user/{userName}")
    public String orderConfig(@PathVariable("userName") String userName,RedirectAttributes redirectAttributes){
        redirectAttributes.addAttribute("userName",userName);       
        redirectAttributes.addFlashAttribute("User_Orders",orderService.getPurchaseOrder(userName));
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/category")
    public String showCategories(RedirectAttributes redirectAttributes){
        List<ProductCategory> category = productService.getCategories();
        ProductCategory newCategory = new ProductCategory();
        redirectAttributes.addFlashAttribute("category", category);
        redirectAttributes.addFlashAttribute("newCategory",newCategory);
        return "redirect:/admin/dashboard";
    }

    @PostMapping("/add-category")
    public String addCategory(ProductCategory category,RedirectAttributes redirectAttributes){
        boolean inserted = productService.addCategory(category);
        redirectAttributes.addAttribute("categoryStatus",inserted);
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/remove-category/{categoryName}")
    public String removeCategory(@PathVariable("categoryName") String categoryName,RedirectAttributes redirectAttributes){
        boolean deleted = productService.removeCategory(categoryName);
        redirectAttributes.addAttribute("categoryStatus",deleted);
        return "redirect:/admin/dashboard";
    }

    @PostMapping("/edit-user")
    public String editUser(User user,RedirectAttributes redirectAttributes){
        userService.saveUser(user);
        redirectAttributes.addFlashAttribute("User_Data",user);
        return "redirect:/admin/dashboard";
    }
}
