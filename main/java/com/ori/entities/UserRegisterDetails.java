package com.ori.entities;

public class UserRegisterDetails {
	
	private String email;
	private String password;
	private String repeatPassword;
	
	
	public UserRegisterDetails(String email, String password, String repeatPassword) {
		super();
		this.email = email;
		this.password = password;
		this.repeatPassword = repeatPassword;
	}


	public UserRegisterDetails() {
		
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getRepeatPassword() {
		return repeatPassword;
	}


	public void setRepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}


	@Override
	public String toString() {
		return "UserRegisterDetails [email=" + email + ", password=" + password + ", repeatPassword=" + repeatPassword
				+ "]";
	}
	
	
	
	
	
	

}
