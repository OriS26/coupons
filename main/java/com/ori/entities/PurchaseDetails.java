package com.ori.entities;

public class PurchaseDetails {
	
	private int purchaseAmount;
	private long couponId;
	
	public PurchaseDetails(int purchaseAmount, long couponId) {
		super();
		this.purchaseAmount = purchaseAmount;
		this.couponId = couponId;
	}

	
	
	
	public PurchaseDetails() {
		
	}


	

	public int getPurchaseAmount() {
		return purchaseAmount;
	}




	public void setPurchaseAmount(int purchaseAmount) {
		this.purchaseAmount = purchaseAmount;
	}




	public long getCouponId() {
		return couponId;
	}

	public void setCouponId(long couponId) {
		this.couponId = couponId;
	}





	
	


	
	
}
