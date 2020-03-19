package com.ori.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ori.controller.CouponController;
import com.ori.dao.ICouponDao;
import com.ori.data.UserLoginData;
import com.ori.entities.Company;
import com.ori.entities.Coupon;
import com.ori.entities.Customer;
import com.ori.entities.User;
import com.ori.entities.UserLoginDetails;
import com.ori.enums.CouponCategory;
import com.ori.enums.ErrorTypes;
import com.ori.enums.UserType;
import com.ori.exceptions.ApplicationException;

@RestController
@RequestMapping("/coupon")
public class CouponApi {
	
	
	@Autowired
	private CouponController couponController;
	
	@Autowired 
	ICouponDao couponDao;
	
//  URL : http://localhost:8080/coupon
		@PostMapping
		public void addCoupon(@RequestBody Coupon coupon, HttpServletRequest request) throws ApplicationException {
			UserLoginData userData = (UserLoginData) request.getAttribute("userLoginData");
			this.couponController.createCoupon(coupon , userData.getCompanyId());
			System.out.println(coupon);
		}
		
		//  URL : http://localhost:8080/coupon
		@PutMapping
		public void updateCoupon(@RequestBody Coupon coupon, HttpServletRequest request) throws ApplicationException {
			
			UserLoginData userLoginData = (UserLoginData) request.getAttribute("userLoginData");
			Long companyId = userLoginData.getCompanyId();
			
			this.couponController.updateCoupon(coupon, companyId);
			System.out.println(coupon);
		}
		
		// http://localhost:8080/coupon/12
		@GetMapping("{id}")
		public Coupon getOneCoupon(@PathVariable("id") long id, HttpServletRequest request) throws ApplicationException {
			
			UserLoginData userLoginData = (UserLoginData) request.getAttribute("userLoginData");
			Long companyId = userLoginData.getCompanyId();
			
			boolean isAuthorized = couponController.isAuthorized(id, companyId);
			
			if (isAuthorized) {
				return this.couponController.getOneCoupon(id);
			}
			return null;

			
			
		}
		
		// http://localhost:8080/coupon/12
		@DeleteMapping("{id}")
		public void deleteCoupon(@PathVariable("id") long id, HttpServletRequest request) throws ApplicationException {
			
			UserLoginData userLoginData = (UserLoginData) request.getAttribute("userLoginData");
			Long companyId = userLoginData.getCompanyId();
			
			boolean isAuthorized = couponController.isAuthorized(id, companyId);
			
			if (isAuthorized) {
				couponController.deleteCoupon(id);
			}
			System.out.println(id + " has been deleted");
		}
		
		
		//  URL : http://localhost:8080/coupon
		@GetMapping
		public List<Coupon> getAllCoupons() throws ApplicationException{
			
			

			return (List<Coupon>) this.couponController.getAllCoupons();
		}
		
		@GetMapping("/couponsByCompany")
		public List<Coupon> getAllCompanysCoupons(long id) throws ApplicationException {
			
			return this.couponController.getAllCompnysCoupons(id);
		}
		
						
		@GetMapping("/CompanyMaxPrice")
		public List<Coupon> findByCompanyIdAndPriceLessThan(@RequestParam("companyId") Long companyId, @RequestParam("price") float price) throws ApplicationException {
			
			return this.couponController.findByCompanyIdAndPriceLessThan(companyId, price);
		}
		
			
		
//		@GetMapping("/Category")
//		public List<Coupon> getCompanyCouponsByCategory(@RequestParam("category") CouponCategory category) throws ApplicationException {
//			return this.couponController.getCompanyCouponsByCategory(category);
//		}
		
		@GetMapping("/MaxPrice")
		public List<Coupon> getCouponsByMaxPrice(@RequestParam("maxPrice") float price) throws ApplicationException {
			return this.couponController.getCouponByMaxPrice(price);
		}
		
		
		@GetMapping("/myCompanyCouponDelete")
		public void myCompanyCouponDelete (HttpServletRequest request, long couponId) throws ApplicationException {
			
			UserLoginData userLoginData = (UserLoginData) request.getAttribute("userLoginData");
			Long companyId = userLoginData.getCompanyId();
			
			
			try {
				
				boolean isAuthorized = couponController.isAuthorized(couponId, companyId);
				
				if (isAuthorized) {
					this.couponController.deleteCoupon(couponId);
				} 
			} catch (Exception e) {
				throw new ApplicationException(ErrorTypes.COUPON_OPERATION_UNAUTHORIZED, "Cannot change other company coupons");
			}
			
			
		}
		
		
		@PutMapping("/myCompanyCouponEdit")
		public void myCompanyCouponEdit (@RequestBody Coupon coupon, HttpServletRequest request) throws ApplicationException {
			
			
			UserLoginData userLoginData = (UserLoginData) request.getAttribute("userLoginData");
			Long companyId = userLoginData.getCompanyId();
			
			
				boolean isAuthorized = couponController.isAuthorized(coupon.getCouponId(), companyId);
				
				if (isAuthorized) {
					
					
					couponController.createCoupon(coupon, companyId);
				
			
				}
		}
		
		
		
		
		
		@GetMapping("/myCompanyCoupons")
		public List<Coupon> getCompanyCoupons(HttpServletRequest request) throws ApplicationException {
			
			UserLoginData userLoginData = (UserLoginData) request.getAttribute("userLoginData");
			
			if(userLoginData.getUserType() != UserType.COMPANY) {
				
				throw new ApplicationException(ErrorTypes.UNAUTHROIZED, "UNAUTHORIZED");
			}
			
			Long companyId = userLoginData.getCompanyId();
			
			System.out.println(companyId);
			
			return this.couponController.getCompanyCoupons(companyId);
			
		}
		
		@DeleteMapping("/myCompanyCouponDelete")
		public void myCompanyCouponDelete(@RequestBody long couponId, HttpServletRequest request) throws ApplicationException {
			
			UserLoginData userLoginData = (UserLoginData) request.getAttribute("userLoginData");
			Long companyId = userLoginData.getCompanyId();
			
			boolean isAuthorized = couponController.isAuthorized(couponId, companyId);
			
			if (isAuthorized) {
				couponController.deleteCoupon(couponId);;
			}
			
			
		}
		
		


}
