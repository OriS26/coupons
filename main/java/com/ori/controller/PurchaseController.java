package com.ori.controller;


import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ori.dao.ICompanyDao;
import com.ori.dao.ICouponDao;
import com.ori.dao.ICustomerDao;
import com.ori.dao.IPurchaseDao;
import com.ori.data.UserLoginData;
import com.ori.entities.Coupon;
import com.ori.entities.Customer;
import com.ori.entities.Purchase;
import com.ori.entities.PurchaseDetails;
import com.ori.enums.CouponCategory;
import com.ori.enums.ErrorTypes;
import com.ori.exceptions.ApplicationException;


@Controller
public class PurchaseController {

	@Autowired
	private IPurchaseDao purchaseDao;

	@Autowired
	private ICustomerDao customerDao;

	@Autowired
	private ICompanyDao companyDao;
	
	@Autowired
	private ICouponDao couponDao;
	
	

	public PurchaseController() {

	}

	public void createPurchase (PurchaseDetails purchaseDetails, long customerId) throws ApplicationException {
		Date today = new Date();
		Timestamp timestamp = new Timestamp(today.getTime());
		
		
		try {
			if(purchaseDetails.getPurchaseAmount() < 1) {
			  throw new Exception();
			} 
		} catch (Exception e) {
			throw new ApplicationException(ErrorTypes.INVALID_AMOUNT, "invalid amount of coupon");
		}
		
		
		try {
			if (!purchaseDao.existsById(customerId));
		}  catch (Exception e) {
			throw new ApplicationException(ErrorTypes.INVALID_COUPON_ID, "invalid coupon id");
		}
		
		try {
			
		    Coupon dataBaseCoupon = couponDao.findById(purchaseDetails.getCouponId());
		    Customer dataBaseCustomer = customerDao.findById(customerId).get();
		    
		    System.out.println(dataBaseCoupon.toString());

			Purchase purchase = new Purchase();
			
			purchase.setCustomer(dataBaseCustomer);
			purchase.setCompany(dataBaseCoupon.getCompany());
			purchase.setCoupon(dataBaseCoupon);
			purchase.setPrice(dataBaseCoupon.getPrice());
			purchase.setTitle(dataBaseCoupon.getTitle());
			purchase.setAmount(purchaseDetails.getPurchaseAmount());
			purchase.setTimestamp(timestamp);
			
			
			 this.purchaseDao.save(purchase);
			 
			 

			
		} catch (Exception e) {
			throw new ApplicationException(ErrorTypes.FAIL_TO_CREATE_PURCHASE, "failed to create purchase");
		}
		
		
		

	}

	public void updatePurchase(Purchase purchase) throws ApplicationException {
		try {
			if (purchaseDao.existsByTitle(purchase.getTitle())) {
				throw new Exception();
			}
			this.purchaseDao.save(purchase);
		} catch (Exception e) {
			throw new ApplicationException(ErrorTypes.PURCHASE_TITLE_IS_TAKEN, "Purchase's title is taken, please try another" );
		}
	}


	public void deletePurchase (long id) throws ApplicationException {
		try {
			if (!purchaseDao.existsById(id)) {
				throw new Exception();
			}
			this.purchaseDao.deleteById(id);
		} catch (Exception e) {
			throw new ApplicationException(ErrorTypes.INVALID_PURCHASE, "Please enter a valid id");
		}
	}

	public Optional<Purchase> getPurchaseDetalis(long id) throws ApplicationException {
		try {

			if (!purchaseDao.existsById(id)) {
				throw new Exception();				
			}
			return  purchaseDao.findById(id);
		} catch (Exception e) {
			throw new ApplicationException(ErrorTypes.INVALID_PURCHASE, "Please enter a valid id");
		}

	}

	public List<Purchase> getAllPurchases () throws ApplicationException {
		try {
			return (List<Purchase>) this.purchaseDao.findAll();

		} catch (Exception e) {
			throw new ApplicationException(ErrorTypes.GENERAL_ERROR, "general error, please try again later");

		}


	}

	public List<Purchase> getAllPurchasesByMaxPrice (float price) throws ApplicationException {
		try {

			if (price < 0) {
				throw new Exception();
			}
			return this.purchaseDao.findByPriceLessThan(price);

		} catch (Exception e) {
			throw new ApplicationException(ErrorTypes.INVALID_PRICE, "please enter a valid price");
		}
	}



	public List<Purchase> getAllCustomerPurchasesLessThanPrice (long customerId, float price) throws ApplicationException {

		try {

			if (price < 0) {

				throw new Exception();
			}
		} catch (Exception e) {
			throw new ApplicationException(ErrorTypes.INVALID_PRICE, "please enter a valid price");
		}

		try {

			if (!customerDao.existsById(customerId)) {
				throw new Exception();
			}
		} catch (Exception e) {
			throw new ApplicationException(ErrorTypes.INVALID_CUSTOMER, "please enter a valid id");
		}

		return this.purchaseDao.findByCustomerUserIdAndCouponPriceLessThan(customerId, price);

	}

//	public List<Purchase> findByCustomerUserIdAndCouponCategory(CouponCategory category, Long customerId) throws ApplicationException {
//		try {
//			if (!customerDao.existsById(customerId)) {
//				throw new Exception();
//			}
//			return this.purchaseDao.findByCustomerUserIdAndCouponCategory(customerId, category);
//		} catch (Exception e) {
//			throw new ApplicationException(ErrorTypes.INVALID_CUSTOMER, "please enter a valid id");
//		}
//
//	}

	public List<Purchase> findPurchaseByCustomerUserId(long id)  throws ApplicationException {
		try {
			if (!customerDao.existsById(id)) {
				throw new Exception();
			}


			return this.purchaseDao.findPurchaseByCustomerUserId(id);
		} catch (Exception e) {
			throw new ApplicationException(ErrorTypes.INVALID_CUSTOMER, "invalid id, please try another");
		}

	}

	public List<Purchase> findByCompanyId(long id) throws ApplicationException {
		try {

			if (!companyDao.existsById(id)) {
				throw new Exception();
			}
			
			return this.purchaseDao.findByCompany(id); 
		} catch (Exception e) {
			throw new ApplicationException(ErrorTypes.INVALID_COMPANY_ID, "invalid id, please try another");
		}
	}

	public List<Purchase> getMyPurchases(Long customerId) throws ApplicationException {
	
			return  (List<Purchase>) this.purchaseDao.findByCustomerUserId(customerId);
		
		
		
//			throw new ApplicationException(ErrorTypes.GENERAL_ERROR, "failed to get purchase, please try again");
		
	}

	/**
	 * @return the purchaseDao
	 */
	public IPurchaseDao getPurchaseDao() {
		return purchaseDao;
	}

	/**
	 * @param purchaseDao the purchaseDao to set
	 */
	public void setPurchaseDao(IPurchaseDao purchaseDao) {
		this.purchaseDao = purchaseDao;
	}





}



