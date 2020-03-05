package com.ori.entities;

import com.ori.enums.UserType;

public class UserLoginDetails {
	
	private String email;
	private String password;
	
	
	
	
	public UserLoginDetails(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	
	

	
	
	
	public UserLoginDetails() {
		
	}







	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}







	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}







	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}







	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}







	@Override
	public String toString() {
		return "UserLoginDetails [email=" + email + ", password=" + password + "]";
	}
	

	
	


	

	
	
	
}
