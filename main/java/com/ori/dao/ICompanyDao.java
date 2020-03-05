package com.ori.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;

import com.ori.entities.Company;
import com.ori.entities.Coupon;
import com.ori.exceptions.ApplicationException;


public interface ICompanyDao extends CrudRepository<Company, Long> {

	public boolean existsByCompanyName(String companyName);


	
	

}
