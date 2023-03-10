package com.nitish.ecommerce.ecommercesite.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.nitish.ecommerce.ecommercesite.dto.UserProfile;
import com.nitish.ecommerce.ecommercesite.entity.Customer;
import com.nitish.ecommerce.ecommercesite.entity.User;
import com.nitish.ecommerce.ecommercesite.repository.CustomerRepository;
import com.nitish.ecommerce.ecommercesite.repository.UserRepository;

@Service
public class UserService{
    
    private UserRepository userRepo;
    private CustomerRepository customerRepo;
    private BCryptPasswordEncoder encoder;
    private ModelMapper mapper;

    @Autowired
    public UserService(
        
        BCryptPasswordEncoder encoder, 
        UserRepository userRepo,
        ModelMapper mapper,
        CustomerRepository customerRepo
    ){
        this.userRepo = userRepo;
        this.encoder = encoder;
        this.mapper = mapper;
        this.customerRepo = customerRepo;
    }

    public void saveUser(UserProfile userProfile){
        String encodedPassword = encoder.encode(userProfile.getPassword());
        userProfile.setPassword(encodedPassword);

        User user = mapper.map(userProfile, User.class);
        Customer customer = mapper.map(userProfile, Customer.class);
        //confirm password is also set so that the validation does not produce error
        user.setRole("USER");
        userRepo.save(user);
        customerRepo.save(customer);
    } 

    public Customer getCustomerByName(String userName){
        Customer customer = customerRepo.findByName(userName);
        if(customer == null){
            customer = new Customer();
        }
        return customer;
    }

    public List<User> findAllUsers() {
        return userRepo.findAll();
    }

    public List<Customer> findAllCustomers(){
        return customerRepo.findAll();
    }

    public Object findByName(String userName) {
        return userRepo.findByName(userName);
    }

    public boolean saveUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        boolean updated = false;
        try{
            userRepo.save(user);
            updated = true;
        }catch(Exception exc)
        {
            exc.printStackTrace();
        }
        return updated;
    }
}