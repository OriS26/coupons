package com.ori.controller;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ori.dao.ICompanyDao;
import com.ori.dao.ICouponDao;
import com.ori.entities.Company;
import com.ori.entities.Coupon;
import com.ori.entities.Customer;
import com.ori.enums.CouponCategory;
import com.ori.enums.ErrorTypes;
import com.ori.exceptions.ApplicationException;

@Controller
public class CouponController  {

	@Autowired
	private ICouponDao couponDao;

	@Autowired
	private CompanyController companyController;

	@Autowired
	private PurchaseController purchaseController;
	
	@Autowired 
	private ICompanyDao companyDao;

	public CouponController() {
	}



	public Coupon createCoupon (Coupon coupon, Long companyId) throws ApplicationException {

		try {
			Company company = companyDao.findById(companyId).get();
			coupon.setCompany(company);
			validateCouponDetalis(coupon);
			return this.couponDao.save(coupon);
		} catch (Exception e) {
			throw new ApplicationException(ErrorTypes.FAILED_TO_CREATE_COUPON, "Failed to create a coupon");

		}
	}

	private void validateCouponDetalis(Coupon coupon) throws ApplicationException {
//		try {

//			if (this.couponDao.existsByTitle(coupon.getTitle())) {
//				throw new Exception();
//			}
//
//		} catch (Exception e) {
//			throw new ApplicationException(ErrorTypes.INVALID_COUPON_NAME, "Coupon name is already exists.");
//		}

		try {

			if (coupon.getPrice() < 0) {
				throw new Exception();
			}
		} catch (Exception e) {
			throw new ApplicationException(ErrorTypes.INVALID_PRICE, "Price must be above 0.");

		}
//		Date today = Calendar.getInstance().getTime();


//		try {
//
//			if (coupon.getStartDate().compareTo(today) < 0) {
//				throw new Exception();
//			}
//		
//		} catch (Exception e) {
//			throw new ApplicationException(ErrorTypes.INVALID_START_DATE, "Start date has already passed.");
//		}

		try {

			if (coupon.getExpiryDate().compareTo(coupon.getStartDate()) < 0) {
				throw new Exception();
			}
		} catch (Exception e) {
			throw new ApplicationException(ErrorTypes.INVALID_END_DATE, "End date cannot be before the start date.");
		}


	}

	
	
	public void removeExpiredCoupon() throws ApplicationException {
		Date today = new Date(Calendar.getInstance().getTime().getTime());
		
		try {
			couponDao.deleteByExpiryDateLessThan(today);
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorTypes.GENERAL_ERROR, "couldn't remove the expired coupons");
		}
	}

	public void updateCoupon(Coupon coupon, Long companyId) throws ApplicationException {
		
		try {
			Coupon couponDataBase = couponDao.findById(coupon.getCouponId()); 
			
			if (couponDataBase.getCompany().getCompanyId() != companyId) {
				throw new Exception();
			} 
		} catch (Exception e) {
			throw new ApplicationException(ErrorTypes.COUPON_OPERATION_UNAUTHORIZED, "Cannot delete other company coupons");
		}
		
		
		try {
			validateCouponDetalis(coupon);
			this.couponDao.save(coupon);
		} catch (Exception e) {
			throw new ApplicationException(ErrorTypes.COUPON_UPDATE_FAILED,	"Failed to update coupon");
		}
	}


	public void deleteCoupon(long couponId) throws ApplicationException {
		
	
		
		try {
			if (!this.couponDao.existsById(couponId)) {
				throw new Exception();
			}
			this.couponDao.deleteById(couponId);
		} catch (Exception e) {
			throw new ApplicationException(ErrorTypes.INVALID_COUPON_ID, "Invalid coupon's id");
		}


	}
	
	
	
	
	public boolean isAuthorized (long couponId, long companyId) throws ApplicationException {
		
		try {
			Coupon couponDataBase = couponDao.findById(couponId); 
			
			if (couponDataBase.getCompany().getCompanyId() == companyId) {
				return true;
			} 
		} catch (Exception e) {
			throw new ApplicationException(ErrorTypes.COUPON_OPERATION_UNAUTHORIZED, "Cannot change other company coupons");
		}
		
		return false;
		
	}
	

	public List<Coupon> getAllCoupons() throws ApplicationException {
		try {
			return (List<Coupon>) this.couponDao.findAll();

		} catch (Exception e) {
			throw new ApplicationException(ErrorTypes.GENERAL_ERROR, "general error, please try again later");
		}
	}

	public Coupon getOneCoupon(long couponId) throws ApplicationException {
		try {

			if (!this.couponDao.existsById(couponId)) {
				throw new Exception();
			}

			return this.couponDao.findById(couponId);

		} catch (Exception e) {

			throw new ApplicationException(ErrorTypes.INVALID_COUPON_ID, "please enter a valid id");
		}
	}

	public List<Coupon> getAllCompnysCoupons(long companyId) throws ApplicationException  {
		try {
			if (!this.companyController.getCompanyDao().existsById(companyId)) {
				throw new Exception();
			}
			return (List<Coupon>) this.couponDao.findByCompany(companyId);
		} catch (Exception e) {
			throw new ApplicationException(ErrorTypes.INVALID_COMPANY_ID, "please enter a valid id");
		}

	}

//	public List<Coupon> getCompanyCouponsByCategory(CouponCategory category) throws ApplicationException {
//
//		try {
//			return  (List<Coupon>)this.couponDao.findByCategory(category);
//
//		} catch (Exception e) {
//			throw new ApplicationException(ErrorTypes.GENERAL_ERROR, "please try again later");
//		}
//	}



	public List<Coupon> getCouponByMaxPrice(float price) throws ApplicationException {

		try {
			if (price < 0) {
				throw new Exception();
			}

			return (List<Coupon>) this.couponDao.findByPriceLessThan(price);
		} catch (Exception e) {
			throw new ApplicationException(ErrorTypes.INVALID_PRICE, "please enter a valid price");
		}

	}
	
	
	



	public List<Coupon> findByCompanyIdAndPriceLessThan(long id, float price) throws ApplicationException {
		try {
			if (price < 0) {
				throw new Exception();
			}
		}  catch (Exception e) {
			throw new ApplicationException(ErrorTypes.INVALID_PRICE, "please enter a valid price");
		}

		try {

			if (!companyController.getCompanyDao().existsById(id)){

				throw new Exception();
			}
			return this.couponDao.findByCompanyAndPriceLessThan(id, price);
		}  catch (Exception e) {

			throw new ApplicationException(ErrorTypes.INVALID_COMPANY_ID, "please enter a valid id");
		}

	}

	/**
	 * @return the couponDbdao
	 */
	public ICouponDao getCouponDao() {
		return couponDao;
	}


	/**
	 * @param couponDao the couponDbdao to set
	 */
	public void setCouponDao(ICouponDao couponDao) {
		this.couponDao = couponDao;
	}



	public List<Coupon> getCompanyCoupons(Long companyId) throws ApplicationException {

//		try {
			
			Optional<Company> companyDatabase = companyDao.findById(companyId);
			
			
		
		
			System.out.println(companyId);
			return this.couponDao.findByCompany(companyDatabase); 
//		} catch (Exception e) {
//			throw new ApplicationException(ErrorTypes.GENERAL_ERROR, "Failed to get coupons");
//		}
		
	}







}
