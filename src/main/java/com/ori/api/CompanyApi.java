package com.ori.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ori.controller.CompanyController;
import com.ori.entities.Company;
import com.ori.exceptions.ApplicationException;

@RestController
@RequestMapping("/companies")

public class CompanyApi {
	
	@Autowired
	private CompanyController companyController;
	
//  URL : http://localhost:8080/companies
		@PostMapping
		public void addCompany(@RequestBody Company company) throws ApplicationException {
			
			this.companyController.createCompany(company);
			System.out.println(company);
		}
		
		//  URL : http://localhost:8080/companies
		@PutMapping
		public void updateCompany(@RequestBody Company company) throws ApplicationException {
			System.out.println(company);
			this.companyController.updateCompany(company);
			System.out.println(company);
		}
		
		// http://localhost:8080/companies/12
		@GetMapping("{id}")
		public Optional<Company> getCompanyDetails(@PathVariable("id") long id) throws ApplicationException {
			
			
			System.out.println(id);
			return this.companyController.getCompanyDetails(id);
		}
		
		// http://localhost:8080/companies/12
		@DeleteMapping("{id}")
		public void deleteCompany(@PathVariable("id") long id) throws ApplicationException {

			this.companyController.deleteCompany(id);
		}
		
		
		//  URL : http://localhost:8080/companies
		@GetMapping
		public List<Company> getAllCompanies() throws ApplicationException {
			
			return this.companyController.getAllCompanies();
		}
		
		
		

}
