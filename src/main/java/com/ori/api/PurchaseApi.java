package com.ori.api;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.swing.JOptionPane;

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
import com.ori.controller.PurchaseController;
import com.ori.data.UserLoginData;
import com.ori.entities.Coupon;
import com.ori.entities.PurchaseDetails;
import com.ori.entities.Purchase;
import com.ori.enums.CouponCategory;
import com.ori.exceptions.ApplicationException;
@RestController
@RequestMapping("/purchases")
public class PurchaseApi {
	
	@Autowired
	private PurchaseController purchaseController;
	
	
	
	
	
//  URL : http://localhost:8080/purchases
		@PostMapping
		public void addPurchase(@RequestBody PurchaseDetails purchaseDetails, HttpServletRequest request) throws ApplicationException {
			
			System.out.println(purchaseDetails + "________________________________________________________");
		
//			PurchaseDetails purchaseDetails = (PurchaseDetails) request.getAttribute("purchaseDetails"); 
//		    long couponId = purchaseDetails.getCouponId();
//		    int amount = purchaseDetails.getAmount();
			
			UserLoginData userLoginData = (UserLoginData) request.getAttribute("userLoginData");
			long customerId = userLoginData.getId();
			
			
			

					
			
			this.purchaseController.createPurchase(purchaseDetails, customerId);
			
		}
		
		//  URL : http://localhost:8080/purchases
		@PutMapping
		public void updatePurchase(@RequestBody Purchase purchase) throws ApplicationException {
			
			this.purchaseController.updatePurchase(purchase);
			
			System.out.println(purchase);
		}
		
		// http://localhost:8080/purchases/12
		@GetMapping("{id}")
		public Optional<Purchase> getPurchaseDetails(@PathVariable("id") long id) throws ApplicationException {
			System.out.println(id);
			
			return this.purchaseController.getPurchaseDetalis(id);
		}
		
		// http://localhost:8080/purchases/12
		@DeleteMapping("{id}")
		public void deletePurchase(@PathVariable("id") long id) throws ApplicationException {
			
			this.purchaseController.deletePurchase(id);
			System.out.println(id + " has been deleted");
		}
		
		
		//  URL : http://localhost:8080/purchases
		@GetMapping
		public List<Purchase> getAllPurchase() throws ApplicationException {

			
			return this.purchaseController.getAllPurchases();
		}
		
		@GetMapping("/findByCustomerId")
		public List<Purchase> findPurchaseByCustomerUserId (@RequestParam("customerId") long id) throws ApplicationException {
			
			return this.purchaseController.findPurchaseByCustomerUserId(id);
		}
		
		@GetMapping("/findByCompanyId")
		public List<Purchase> findByCompanyId (@RequestParam("companyId") long id) throws ApplicationException {
			
			return this.purchaseController.findByCompanyId(id);
		}
		
		
//		@GetMapping("/findByCustomerAndCategory")
//		public List<Purchase> findByCustomerUserIdAndCouponCategory(@RequestParam("category") CouponCategory category, Long customerId, HttpServletRequest request) throws ApplicationException {
//			UserLoginData userLoginData = (UserLoginData) request.getAttribute("userLoginData");
//			customerId = userLoginData.getId();
//			
//			List<Purchase> purchases = purchaseController.findByCustomerUserIdAndCouponCategory(category, customerId);
//			return purchases;
//		}
		
		@GetMapping("/allPurchasesByMaxPrice")
		public List<Purchase> getAllPurchasesByMaxPrice (@RequestParam("maxPrice") float price) throws ApplicationException {
			
			return this.purchaseController.getAllPurchasesByMaxPrice(price);
		}
		
	   
		
		@GetMapping("/allCustomerPurchases")
		public List<Purchase> getAllCustomerPurchases(@RequestParam("maxPrice") float price, Long customerId, HttpServletRequest request) throws ApplicationException {
			UserLoginData userLoginData = (UserLoginData) request.getAttribute("userLoginData");
			customerId = userLoginData.getId();
			 
			List<Purchase> purchases = purchaseController.getAllCustomerPurchasesLessThanPrice(customerId, price);
			return purchases;
		}
		
		@GetMapping("/myPurchases")
		public List<Purchase> getMyPurchases(HttpServletRequest request) throws ApplicationException {
			UserLoginData userLoginData = (UserLoginData) request.getAttribute("userLoginData");
			Long customerId = userLoginData.getId();
			System.out.println("going to controller");
			List<Purchase> purchases = purchaseController.getMyPurchases(customerId);
			return (List<Purchase>) purchases;
			
		}

}
