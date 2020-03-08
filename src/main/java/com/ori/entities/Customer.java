package com.ori.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ori.enums.CustomerStatus;

@Entity
@Table(name="CUSTOMERS")
public class Customer {

	@Id
	@GeneratedValue
	private long customerId;
	
	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@MapsId
	private User user;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.REMOVE, fetch=FetchType.LAZY, mappedBy="customer")
	private List<Purchase> purchases;

	
	
	@Column(name="IS_MARRIED")
	private CustomerStatus married;

	@Column(name="AMOUNT_OF_KIDS")
	private int amountOfKids;

	@Column(name="ADDRESS")
	private String address;

	@Column(name="PHONE")
	private int phone;

	@Column(name="NAME", nullable=false)
	private String name;


	public Customer() {

	}


	
	
	/**
	 * @return the customerId
	 */
	public long getCustomerId() {
		return customerId;
	}




	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}




	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}


	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}


	/**
	 * @return the purchases
	 */
	public List<Purchase> getPurchases() {
		return purchases;
	}


	/**
	 * @param purchases the purchases to set
	 */
	public void setPurchases(List<Purchase> purchases) {
		this.purchases = purchases;
	}






	/**
	 * @return the married
	 */
	public CustomerStatus getMarried() {
		return married;
	}




	/**
	 * @param married the married to set
	 */
	public void setMarried(CustomerStatus married) {
		this.married = married;
	}




	/**
	 * @return the amountOfKids
	 */
	public int getAmountOfKids() {
		return amountOfKids;
	}


	/**
	 * @param amountOfKids the amountOfKids to set
	 */
	public void setAmountOfKids(int amountOfKids) {
		this.amountOfKids = amountOfKids;
	}

 
	public String getAddress() {
		return address;
	}




	public void setAddress(String address) {
		this.address = address;
	}




	/**
	 * @return the phone
	 */
	public int getPhone() {
		return phone;
	}


	/**
	 * @param phone the phone to set
	 */
	public void setPhone(int phone) {
		this.phone = phone;
	}


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}


	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}




	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", user=" + user + ", purchases=" + purchases + ", amountOfKids="
				+ amountOfKids + ", adress=" + address + ", phone=" + phone + ", name=" + name + "]";
	}




	

	

	


	//	private boolean isMarried;
	//	private int amountOfKids;
	//	private String address;
	//	private User user;
	//	Long customerId;
	//	private int phone;
	//	private String name;







	//	public Customer(Long customerId, boolean isMarried, int amountOfKids, String address, String type,  int phone,
	//			        String name) {
	//		this.customerId = customerId;
	//		this.isMarried = isMarried;
	//		this.amountOfKids = amountOfKids;
	//		this.address = address;
	//		this.phone = phone;
	//		this.name = name;
	//	}
	//
	//	
	//	public Customer(boolean isMarried, int amountOfKids, String address, User user) {
	//		this.isMarried = isMarried;
	//		this.amountOfKids = amountOfKids;
	//		this.address = address;
	//		this.user = user;
	//		
	//	}




}
