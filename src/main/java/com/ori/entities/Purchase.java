package com.ori.entities;

import java.sql.Timestamp;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ori.utils.JsonTimestampSerializer;

@Entity
@Table(name="PURCHASES")
public class Purchase {
	
	@Id
	@GeneratedValue
	private long purchaseId;
	
	
	@ManyToOne
	private Coupon coupon;
	
	
	@ManyToOne
	private Customer customer;
	

	@ManyToOne
	private Company company;
	
	
//	@Column(name="COMPANY_ID")
//	private long companyId;


	@Column(name="TITLE")
	private String title;

	/**
	 * @return the company
	 */
	public Company getCompany() {
		return company;
	}




	/**
	 * @param company the company to set
	 */
	public void setCompany(Company company) {
		this.company = company;
	}









	@Column(name="PRICE")
	private  float price;
	
	@Column(name="AMOUNT")
	private int amount;
	
	
	@CreationTimestamp
	@Column(name="TIME_STAMP")
	private Timestamp timestamp;
	
	
	
	public Purchase() {
		

		
		
		
	}

	


	/**
	 * @return the purchaseId
	 */
	public long getPurchaseId() {
		return purchaseId;
	}



	/**
	 * @param purchaseId the purchaseId to set
	 */
	public void setPurchaseId(long purchaseId) {
		this.purchaseId = purchaseId;
	}



	/**
	 * @return the coupon
	 */
	public Coupon getCoupon() {
		return coupon;
	}



	/**
	 * @param coupon the coupon to set
	 */
	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}



	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}



	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	
	



//	public long getCompanyId() {
//		return companyId;
//	}
//
//
//
//
//	public void setCompanyId(long companyId) {
//		this.companyId = companyId;
//	}




	public int getAmount() {
		return amount;
	}




	public void setAmount(int amount) {
		this.amount = amount;
	}




	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}



	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}



	/**
	 * @return the price
	 */
	public float getPrice() {
		return price;
	}



	/**
	 * @param price the price to set
	 */
	public void setPrice(float price) {
		this.price = price;
	}



	@JsonSerialize(using = JsonTimestampSerializer.class)
	public Timestamp getTimestamp() {
		return timestamp;
	}



	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}




	




	public void setCoupon(Optional<Coupon> dataBaseCoupon) {
		// TODO Auto-generated method stub
		
	}



	
	
	
	
	
}
