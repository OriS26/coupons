package com.ori.entities;

import com.ori.enums.UserType;

public class SuccessfulLoginData {
	
	private int token;
	private UserType userType;

	public SuccessfulLoginData() {
	}

	public SuccessfulLoginData(int token, UserType userType) {
		this.token = token;
		this.userType = userType;
	}

	public int getToken() {
		return token;
	}

	public void setToken(int token) {
		this.token = token;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}


}
