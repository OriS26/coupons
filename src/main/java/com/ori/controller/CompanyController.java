package com.ori.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ori.dao.ICompanyDao;
import com.ori.entities.Company;
import com.ori.enums.ErrorTypes;
import com.ori.exceptions.ApplicationException;

@Controller
public class CompanyController  {

	@Autowired
	private ICompanyDao companyDao;

	public CompanyController() {

	}


	public Long createCompany (Company company) throws ApplicationException {

		try {

			validateCreateCompany(company);


			this.companyDao.save(company);
			Long id = company.getCompanyId();
			return id;
		} catch (Exception e) {
			throw new ApplicationException(ErrorTypes.FAILED_TO_CREARE_COMPANY, "Failed to create company");  
		}



	}



	private void validateCreateCompany(Company company) throws ApplicationException {

		try {

			if (this.companyDao.existsByCompanyName(company.getCompanyName())) {
				throw new Exception();
			}
		} catch (Exception e) {
			throw new ApplicationException(ErrorTypes.INVALID_COMPANY_NAME, "Name already exists");
		}

	}




	public void updateCompany(Company company) throws ApplicationException {

		validateCreateCompany(company);
		
		try {


			this.companyDao.save(company);
		} catch (Exception e) {
			throw new ApplicationException(ErrorTypes.COMPANY_UPDATE_FAILED, "Failed to update company, please try again later");  
		}
	}

	public void deleteCompany(long id) throws ApplicationException {

		try {
			if (!companyDao.existsById(id)) {
				throw new Exception();
			}
			this.companyDao.deleteById(id);
		} catch (Exception e) {
			throw new ApplicationException(ErrorTypes.INVALID_COMPANY_ID, "Please enter a valid id");
		}
	}

	public  List<Company> getAllCompanies() throws ApplicationException {

		try {

			return (List<Company>) this.companyDao.findAll();
		} catch (Exception e) {
			throw new ApplicationException(ErrorTypes.GENERAL_ERROR, "General error, please try again later");
		}

	}



	public Optional<Company>  getCompanyDetails(long companyId) throws ApplicationException{
		try {
			if (!companyDao.existsById(companyId)) {
				throw new Exception();
			}
			return this.companyDao.findById(companyId);
		} catch (Exception e) {
			throw new ApplicationException(ErrorTypes.INVALID_COMPANY_ID, "Please enter a valid id");
		}


	}

	/**
	 * @return the companyDbdao
	 */
	public ICompanyDao getCompanyDao() {
		return companyDao;
	}

	/**
	 * @param companyDao the companyDbdao to set
	 */
	public void setCompanyDao(ICompanyDao companyDao) {
		this.companyDao = companyDao;
	}

}

