package com.nitish.ecommerce.ecommercesite.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.nitish.ecommerce.ecommercesite.entity.User;
import com.nitish.ecommerce.ecommercesite.repository.UserRepository;

@Component
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepo.findByName(username);
		if(user == null){
			//returning empty user in case of not found
			user = new User();
		}
		return new MyPrincipal(user);
	}

}
