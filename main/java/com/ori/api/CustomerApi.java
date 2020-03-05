package com.ori.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.DefaultEditorKit.CutAction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ori.controller.CustomerController;
import com.ori.data.UserLoginData;
import com.ori.entities.Customer;
import com.ori.entities.RegisterDetails;
import com.ori.entities.User;
import com.ori.exceptions.ApplicationException;

@RestController
@RequestMapping("/customers")



public class CustomerApi {
	
	@Autowired
	private CustomerController customerController;
	
//  URL : http://localhost:8080/customers
		@PostMapping
		public void addCustomer(@RequestBody RegisterDetails registerDetails) throws ApplicationException {
			
			this.customerController.createCustomer(registerDetails);
			System.out.println(registerDetails);
			
		}
		
		//  URL : http://localhost:8080/customers
		@PutMapping
		public void updateCustomer(@RequestBody RegisterDetails registerDetails, HttpServletRequest request) throws ApplicationException {
			
			UserLoginData userLoginData = (UserLoginData) request.getAttribute("userLoginData");
			long customerId = userLoginData.getId();
			
			this.customerController.updateCustomer(registerDetails, customerId);
			System.out.println(registerDetails);
		}
		
		// http://localhost:8080/customers/12345
		@GetMapping("{customerId}")
		public Customer getCustomerDetails(@PathVariable("customerId") long id) throws ApplicationException {
			System.out.println(id);
			
			return this.customerController.getCustomerDetails(id);
		}
		
		// c
		@DeleteMapping("{customerId}")
		public void deleteCompany(@PathVariable("customerId") long id) throws ApplicationException {
			
			this.customerController.deleteCustomer(id);
			System.out.println(id + "has been deleted");
		}
		
		
		//  URL : http://localhost:8080/customers
		@GetMapping
		public List<Customer> getAllCustomers() throws ApplicationException{
			 
			
			
			return this.customerController.getAllCustomers();
		}
		
		// http://localhost:8080/customers/myDetails
				@GetMapping("/myDetails")
				public Customer getMyCustomerDetails(HttpServletRequest request) throws ApplicationException {
					System.out.println("going to controller");
					UserLoginData userLoginData = (UserLoginData) request.getAttribute("userLoginData");
					long customerId = userLoginData.getId();
					
					 Customer customer =  customerController.getMyCustomerDetails(customerId);
					 return customer;
				}
				
				@PutMapping("/myDetailsEdit")
				public void editMyCustomerDetails(@RequestBody RegisterDetails registerDetails, HttpServletRequest request) throws ApplicationException {
					
					UserLoginData userLoginData = (UserLoginData) request.getAttribute("userLoginData");
					long customerId = userLoginData.getId();
					
					
					 customerController.createCustomer(registerDetails);
					 
					
				}

}
