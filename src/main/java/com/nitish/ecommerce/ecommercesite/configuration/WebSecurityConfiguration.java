package com.nitish.ecommerce.ecommercesite.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfiguration {

	@Autowired
	private MyUserDetailsService authProvider;
	
	@Bean 
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.httpBasic();
		http
		.authorizeHttpRequests((requests) -> 
			requests
			.antMatchers("/","/login","/register","/styles/**","/js/**").permitAll()
			.antMatchers("/add-product/**").hasAnyAuthority("ROLE_ADMIN")
			.antMatchers("/admin/**").hasAnyAuthority("ROLE_ADMIN")
			.anyRequest().authenticated()
		);
		http.formLogin((form)->{
			form.loginPage("/login");
		}).logout((logout)->{
			logout.logoutUrl("/logout").logoutSuccessUrl("/login");
		});
		
		return http.build();
	}
	
	@Bean 
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
    public AuthenticationManager authenticationManagerBean(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(authProvider).passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }
}
