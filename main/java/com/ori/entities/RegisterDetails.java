package com.ori.entities;

public class RegisterDetails {
	
	private String name;
	private String address;
	private int	amountOfKids;	
	private int	phone;	
	private boolean isMarried;
	private UserRegisterDetails userRegisterDetails; 
	
	public RegisterDetails(String name, String address, int amountOfKids, int phone, boolean isMarried,  UserRegisterDetails userRegisterDetails) {
		super();
		this.name = name;
		this.address = address;
		this.amountOfKids = amountOfKids;
		this.phone = phone;
		this.isMarried = isMarried;
		this.userRegisterDetails = userRegisterDetails;
	}

	public RegisterDetails() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getAmountOfKids() {
		return amountOfKids;
	}

	public void setAmountOfKids(int amountOfKids) {
		this.amountOfKids = amountOfKids;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public boolean isMarried() {
		return isMarried;
	}

	public void setMarried(boolean isMarried) {
		this.isMarried = isMarried;
	}

	public UserRegisterDetails getUserRegisterDetails() {
		return userRegisterDetails;
	}

	public void setUserRegisterDetails(UserRegisterDetails userRegisterDetails) {
		this.userRegisterDetails = userRegisterDetails;
	}

	@Override
	public String toString() {
		return "RegisterDetails [name=" + name + ", address=" + address + ", amountOfKids=" + amountOfKids + ", phone="
				+ phone + ", isMarried=" + isMarried + ", userRegisterDetails=" + userRegisterDetails.toString() + "]";
	}
	
	
	
	
	
	
	
	
}
