package com.ori.entities;

import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ori.enums.CouponCategory;

@Entity
@Table(name="COUPONS")
public class Coupon {
	
	@Id
	@GeneratedValue
	private long couponId;
	
	
	@ManyToOne
	private Company company;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.REMOVE, fetch=FetchType.LAZY, mappedBy="customer")
	private List<Purchase> purchases;
	
	@Column(name="TITLE",  nullable=false)
	private String title;
	
	@Column(name="PRICE", nullable=false)
	private float price;
	
	@Column(name="AMOUNT", nullable=false)
	private int amount;
	
	@Column(name="CATEGORY")
	private CouponCategory category;
	
	@Column(name="START_DATE", nullable=false)
	private Date startDate;
	
	@Column(name="EXPIRY_DATE", nullable=false)
	private Date expiryDate;
	
	
	
	public Coupon() {
		
	}



	/**
	 * @return the couponId
	 */
	public long getCouponId() {
		return couponId;
	}



	/**
	 * @param couponId the couponId to set
	 */
	public void setCouponId(long couponId) {
		this.couponId = couponId;
	}



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



	






	/**
	 * @return the category
	 */
	public CouponCategory getCategory() {
		return category;
	}



	/**
	 * @param category the category to set
	 */
	public void setCategory(CouponCategory category) {
		this.category = category;
	}



	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}



	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}



	/**
	 * @return the expiryDate
	 */
	public Date getExpiryDate() {
		return expiryDate;
	}



	/**
	 * @param expiryDate the expiryDate to set
	 */
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}



	public void setCompanyId(Long companyId) {
		// TODO Auto-generated method stub
		
	}



	/**
	 * @return the amount
	 */
	public int getAmount() {
		return amount;
	}



	/**
	 * @param amount the amount to set
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}



	@Override
	public String toString() {
		return "Coupon [couponId=" + couponId + ", company=" + company + ", purchases=" + purchases + ", title=" + title
				+ ", price=" + price + ", amount=" + amount + ", startDate=" + startDate + ", expiryDate=" + expiryDate
				+ "]";
	}
	
	
	
	
	
//	private String title;
//	private long couponId;
//	private float price;
//	private long companyId;
//	private CouponCategory category;
//	private Date startDate;
//	private Date expiryDate;
//	
//	
//	
//	public Coupon(String title,  float price,  
//			 Date startDate, Date endDate, long companyId,CouponCategory category) {
//		this.title = title;
//		this.price = price;
//		this.expiryDate = endDate;
//		this.startDate = startDate;
//		this.companyId = companyId;
//		this.category = category;
//		
//	}
//		
//		public Coupon(long couponId, String title, float price, Date startDate, 
//				 Date endDate, long companyId, CouponCategory category) {
//			this(title, price, startDate, endDate, companyId, category);
//			this.couponId = couponId;
//			
//	}

	
		
	

	
	
	
}
	
