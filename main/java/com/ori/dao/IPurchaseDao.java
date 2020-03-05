package com.ori.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ori.entities.Purchase;
import com.ori.enums.CouponCategory;

public interface IPurchaseDao extends CrudRepository<Purchase, Long> {
	

	public List<Purchase> findByCompany(long couponId);
	
	public List<Purchase> findPurchaseByCustomerUserId(long customerId); 
	
//	public List<Purchase> findByCustomerUserIdAndCouponCategory(long customerId, CouponCategory category);
	
	public List<Purchase> findByCustomerUserIdAndCouponPriceLessThan(long customerId, float price);

	public List<Purchase> findByPriceLessThan(float price);

	public boolean existsByTitle(String title);

	public List<Purchase> findByCustomerUserId(Long customerId);
	

}
