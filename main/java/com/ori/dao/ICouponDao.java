package com.ori.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ori.entities.Company;
import com.ori.entities.Coupon;
import com.ori.entities.Customer;
import com.ori.enums.CouponCategory;
import com.ori.exceptions.ApplicationException;


public interface ICouponDao extends CrudRepository<Coupon, Long>{

	public boolean existsByTitle(String title);

	public List<Coupon> findByCompany(Long companyId);

//	public List<Coupon> findByCategory(CouponCategory category);

	public List<Coupon> findByPriceLessThan(float price);

	public List<Coupon> findByCompanyAndPriceLessThan(long id, float price);
	
	public void deleteByExpiryDateLessThan(Date date);
	
	public Coupon findById(long couponId);

	public List<Coupon> findByCompany(Optional<Company> companyDatabase);

	
	
	
	
	

	
	
	
	
}
