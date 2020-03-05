package com.ori.entities;



import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ori.enums.CompanyType;

@Entity
@Table(name="COMPANIES")
public class Company {

	@Id
	@GeneratedValue
	private Long companyId;
	
	@Column(name="COMPANY_NAME", unique=true, nullable=false)
	private String companyName;
	
	@Column(name="ADDRESS")
	private String address;
	
	@Column(name="PHONE", unique=true, nullable=false)
	private String phone;
	
//	@Column(name="TYPE")
//	private CompanyType type;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.REMOVE, fetch=FetchType.LAZY, mappedBy="company")
	List<User> users;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.REMOVE, fetch=FetchType.LAZY, mappedBy="company")
	List<Coupon> coupons;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.REMOVE, fetch=FetchType.LAZY, mappedBy="company")
	List<Purchase> purchases;
	
	
	

	
	public Company() {
		
	}


	


	public List<Purchase> getPurchases() {
		return purchases;
	}





	public void setPurchases(List<Purchase> purchases) {
		this.purchases = purchases;
	}





	/**
	 * @return the companyId
	 */
	public Long getCompanyId() {
		return companyId;
	}





	/**
	 * @param companyId the companyId to set
	 */
	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}





	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}


	/**
	 * @param companyName the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}



	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}



	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}


	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}


	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}


	





	/**
	 * @return the users
	 */
	public List<User> getUsers() {
		return users;
	}





	/**
	 * @param users the users to set
	 */
	public void setUsers(List<User> users) {
		this.users = users;
	}





	/**
	 * @param companyId the companyId to set
	 */
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}





	/**
	 * @return the coupons
	 */
	public List<Coupon> getCoupons() {
		return coupons;
	}


	/**
	 * @param coupons the coupons to set
	 */
	public void setCoupons(List<Coupon> coupons) {
		this.coupons = coupons;
	}





	





	
	
	
	
	
	

	
	

}
