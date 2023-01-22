package com.nitish.ecommerce.ecommercesite.service;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nitish.ecommerce.ecommercesite.dto.ProductOrder;
import com.nitish.ecommerce.ecommercesite.entity.Customer;
import com.nitish.ecommerce.ecommercesite.entity.Order;
import com.nitish.ecommerce.ecommercesite.entity.OrderItem;
import com.nitish.ecommerce.ecommercesite.entity.Product;
import com.nitish.ecommerce.ecommercesite.repository.CustomerRepository;
import com.nitish.ecommerce.ecommercesite.repository.OrderRepository;
import com.nitish.ecommerce.ecommercesite.repository.ProductRepository;

@Service
public class OrderService {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ProductRepository productsRepo;

    @Autowired
    private CustomerRepository customerRepo;

    @Autowired
    private OrderRepository orderRepo;

    public ProductOrder getProductOrder(String productName) {
        Product product = productsRepo.findByName(productName);
        ProductOrder orderItem = mapper.map(product, ProductOrder.class);
        return orderItem;
    }

    public String addItemNew(String customerName, String orderTrackingNo, OrderItem item) {
        Order order = getOrderByUserName(customerName, orderTrackingNo);
        saveOrderItem(customerName, order, item);
        return order.getOrderTrackingNumber();
    }

    public Set<Order> getPurchaseOrder(String userName) {
        System.out.println("OrderService: getPurchaseOrder userName:" + userName);
        Customer customer = customerRepo.findByName(userName);
        Set<Order> orders = customer.getOrders();
        return orders;
    }

    private void saveOrderItem(String customerName, Order order, OrderItem item) {
        Customer customer = customerRepo.findByName(customerName);
        // adding the item to the order
        order.addOrderItem(item);
        order.setCustomer(customer);
        // save the order
        orderRepo.save(order);
    }


    public void savePurchase(String userName, String orderTrackingNo) {
        Customer customer = customerRepo.findByName(userName);
        Order order = getOrderByUserName(userName, orderTrackingNo);
        order.setOrderPurchased(true);
        order.setCustomer(customer);
        orderRepo.save(order);
    }

    public Order getOrderByUserName(String userName, String orderTrackingNo) {
        Order order = null;
        //getting the customer using name
        Customer customer = customerRepo.findByName(userName);
        if (customer != null) {
            //filtering the output by orderTrackingNo
            Optional<Order> filteredOrders = customer.getOrders().stream().filter(
                    x -> x.getOrderTrackingNumber().equalsIgnoreCase(orderTrackingNo))
                    .findAny();
            if (filteredOrders.isPresent()) {
                //if order present then calculating the total and quantity
                order = calculateTotalAndQuantity(filteredOrders.get());
            } else {
                //else creating new order and setting the order tracking no
                order = new Order();
                UUID uuid = UUID.randomUUID();
                order.setOrderTrackingNumber(uuid.toString());
            }
        }
        return order;
    }

    private Order calculateTotalAndQuantity(Order order) {
        BigDecimal total = new BigDecimal(0);
        BigDecimal quantity = new BigDecimal(0);
        for (OrderItem item : order.getOrderItems()) {
            total = total.add(item.getQuantity().multiply(item.getUnitPrice()));
            quantity = quantity.add(item.getQuantity());
        }
        order.setTotalPrice(total);
        order.setTotalQuantity(quantity.intValue());
        return order;
    }

    public Set<Order> cancelOrder(String username, String orderTrackingNo) {
        Customer customer = customerRepo.findByName(username);
        Optional<Order> filteredOrders = customer.getOrders().stream().filter(
                    x -> x.getOrderTrackingNumber().equalsIgnoreCase(orderTrackingNo))
                    .findAny();
        if(filteredOrders.isPresent()){
            orderRepo.delete(filteredOrders.get());
        }
        //refreshing the list
        customer = customerRepo.findByName(username);
        for(Order order: customer.getOrders()){
            System.out.println(order.getOrderTrackingNumber());
        }
        return customer.getOrders();
        
    }
}
