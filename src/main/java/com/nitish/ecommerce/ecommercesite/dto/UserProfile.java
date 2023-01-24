package com.nitish.ecommerce.ecommercesite.dto;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class UserProfile {
    @NotEmpty
    private String password;
    
    @NotEmpty
    private String name;

    @Email
    @NotEmpty
    private String email;

    @NotEmpty
    private String phoneNo;

    @NotEmpty
    private String address;

	@NotEmpty
	private String confirmPassword;
	private boolean passwordEqual;

    public UserProfile(){}
    public UserProfile(String name,String email,String phoneNo,String address,String password){
        this.name = name;
        this.email = email;
        this.phoneNo = phoneNo;
        this.address = address;
        this.password = password;
    }


	public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhoneNo() {
        return phoneNo;
    }
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getConfirmPassword() {
        return confirmPassword;
    }
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
    @AssertTrue(message = "Passwords should match")
	public boolean isPasswordEqual() {
		try {
			return password.equals(confirmPassword);
		} catch(Exception e) {
			return false;
		}
	}
    
}
