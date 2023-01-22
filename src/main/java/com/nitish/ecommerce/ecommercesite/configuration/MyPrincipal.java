package com.nitish.ecommerce.ecommercesite.configuration;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import com.nitish.ecommerce.ecommercesite.entity.User;

public class MyPrincipal implements UserDetails{

	 private User user;
	 
	 @Autowired
	 public MyPrincipal(User user){
	      this.user = user;
	 }
	 
	    
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> grantedAuthority = new  ArrayList<GrantedAuthority>();
		grantedAuthority.add(new SimpleGrantedAuthority("ROLE_"+user.getRole()));
		return grantedAuthority;
	}

	public String getPassword() {
		return user.getPassword();
	}

	public String getUsername() {
		return user.getName();
	}

	public boolean isAccountNonExpired() {
		return true;
	}

	public boolean isAccountNonLocked() {
		return true;
	}

	public boolean isCredentialsNonExpired() {
		return true;
	}

	public boolean isEnabled() {
		return true;
	}

}
