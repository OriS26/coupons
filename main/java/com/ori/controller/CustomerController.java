package com.ori.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ori.dao.ICustomerDao;
import com.ori.entities.Company;
import com.ori.entities.Coupon;
import com.ori.entities.Customer;
import com.ori.entities.RegisterDetails;
import com.ori.entities.User;
import com.ori.enums.ErrorTypes;
import com.ori.enums.UserType;
import com.ori.exceptions.ApplicationException;
import com.ori.utils.JdbcUtils;

@Controller
public class CustomerController  {

	public CustomerController() {
	}

	@Autowired
	private ICustomerDao customerDao;

	@Autowired
	private UserController userController;



	public void createCustomer(RegisterDetails registerDetails) throws ApplicationException {
		
			System.out.println(registerDetails);
		
			String repeatPassword = registerDetails.getUserRegisterDetails().getRepeatPassword();
			Customer customer = new Customer();
			User user = new User();
			
			user.setEmail(registerDetails.getUserRegisterDetails().getEmail());
			user.setPassword(registerDetails.getUserRegisterDetails().getPassword());
			user.setType(UserType.CUSTOMER);
			
			
			
//			customer.getUser().setEmail(registerDetails.getUserRegisterDetails().getEmail());
//			customer.getUser().setPassword(registerDetails.getUserRegisterDetails().getPassword());
			customer.setUser(user);
			customer.setAddress(registerDetails.getAddress());
			customer.setAmountOfKids(registerDetails.getAmountOfKids());
			customer.setName(registerDetails.getName());
			customer.setMarried(registerDetails.isMarried());
//			customer.getUser().setType(UserType.CUSTOMER);
			
			userController.validateCreateUser(customer.getUser(), repeatPassword);
			validateCreateCustomer(customer);
		
		

		try {
			this.customerDao.save(customer);
		} catch (Exception e) {
			throw new ApplicationException(ErrorTypes.CUSTOMER_CREATION_FAILED, "failed to create a customer");
		}
		
		
	}

	private void validateCreateCustomer(Customer customer) throws ApplicationException  {


		if (customer.getAmountOfKids() < 0) {

			throw new ApplicationException(ErrorTypes.INVALID_AMOUNT_OF_KIDS, "Invalid amount of kids.");

		}
	}






	public void updateCustomer(RegisterDetails registerDetails, long customerId) throws ApplicationException {



			String repeatPassword = registerDetails.getUserRegisterDetails().getRepeatPassword();
			Customer customer = new Customer();
			User user = new User();
			
			user.setId(customerId);
			user.setEmail(registerDetails.getUserRegisterDetails().getEmail());
			user.setPassword(registerDetails.getUserRegisterDetails().getPassword());
			user.setType(UserType.CUSTOMER);
			
			customer.setUser(user);
			customer.setAddress(registerDetails.getAddress());
			customer.setAmountOfKids(registerDetails.getAmountOfKids());
			customer.setName(registerDetails.getName());
			customer.setMarried(registerDetails.isMarried());

			
//			userController.validateCreateUser(customer.getUser(), repeatPassword);
//			validateCreateCustomer(customer);

			this.customerDao.save(customer);
			


	}

	private void customerUserValidation(Customer customer) throws ApplicationException{
		User user = customer.getUser();

		try {
			if (this.userController.isUserExistsById(customer.getUser().getId())) {
				throw new Exception();
			}

		} catch (Exception e) {
			throw new ApplicationException(ErrorTypes.INVALID_USER, "User doesn't exists");
		}

		try {

			if (user.getId() != customer.getCustomerId()) {
				throw new Exception();
			}
		} catch (Exception e) {
			throw new ApplicationException(ErrorTypes.INVALID_USER_ID, "User id and customer id doesn't match");
		}


	}

	public void deleteCustomer (long id) throws ApplicationException {

		try {
			if (!this.customerDao.existsById(id)) {
				throw new Exception();
			}

			this.customerDao.deleteById(id);

		} catch (Exception e) {

			throw new ApplicationException(ErrorTypes.INVALID_CUSTOMER, "please enter valid");
		}

	}

	public List<Customer> getAllCustomers () throws ApplicationException {
		try {

			return (List<Customer>) this.customerDao.findAll();
		} catch (Exception e) {
			throw new ApplicationException(ErrorTypes.GENERAL_ERROR, "General error please try later");
		}
	}




	public Customer getCustomerDetails(long id) throws ApplicationException {
		try {
			if (!this.customerDao.existsById(id)) {
				throw new Exception();
			}

		} catch (Exception e) {
			throw new ApplicationException(ErrorTypes.INVALID_CUSTOMER, "please e nter a valid id");
		}

		try {
			return this.customerDao.findById(id).get();
		} catch (Exception e) {
			throw new ApplicationException(ErrorTypes.FAIL_TO_DELETE_CUSTOMER, "Failed to delete customer");
		}
	}


	public boolean isCustomerExistsById(Customer customer) throws ApplicationException {
		try {

			return this.customerDao.existsById(customer.getCustomerId());
		} catch (Exception e) {

			throw new ApplicationException(ErrorTypes.FAIL_TO_CHECK_IF_CUSTOMER_EXIST, "failed to check if customer exsits");

		}
	}


	/**
	 * @return the customerDbdao
	 */
	public ICustomerDao getCustomerDbdao() {
		return customerDao;
	}

	/**
	 * @param customerDbdao the customerDbdao to set
	 */
	public void setCustomerDbdao(ICustomerDao customerDbdao) {
		this.customerDao = customerDbdao;
	}

	public Customer getMyCustomerDetails(long customerId) throws ApplicationException {
		try {
			return this.customerDao.findById(customerId).get();
		} catch (Exception e) {
			throw new ApplicationException(ErrorTypes.GENERAL_ERROR, "failed to get customer details");
		}
		
	}





}
