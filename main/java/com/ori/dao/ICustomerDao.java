package com.ori.dao;


import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ori.entities.Customer;

public interface ICustomerDao extends CrudRepository<Customer, Long>{

	public Customer findByUser(long customerId);
	
//	public Customer findById(long customerId);
	
	
	
	

	

	
	
	
	

}
