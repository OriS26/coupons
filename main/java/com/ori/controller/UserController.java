package com.ori.controller;


import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ori.dao.IUserDao;
import com.ori.data.UserLoginData;
import com.ori.entities.Company;
import com.ori.entities.SuccessfulLoginData;
import com.ori.entities.User;
import com.ori.entities.UserLoginDetails;
import com.ori.enums.ErrorTypes;
import com.ori.enums.UserType;
import com.ori.exceptions.ApplicationException;

@Controller
public class UserController {

	@Autowired
	private IUserDao userDao;

	@Autowired
	private CacheController cacheController;

	@Autowired
	private CustomerController customerController;

	public UserController() {

	}

	private static final String ENCRYPTION_TOKEN_SALT = "AASDFHSJFHJHKAAAAA-3423@#$@#$";



	public SuccessfulLoginData login(UserLoginDetails userLoginDetails ) throws ApplicationException {
		User user;
		try {
			user = this.userDao.findByEmailAndPassword(userLoginDetails.getEmail(), userLoginDetails.getPassword());
		} catch (Exception e) {
			throw new ApplicationException(ErrorTypes.INVALID_EMAIL_OR_PASSWORD, "invalid Email or password");
		}

		if (user == null) {
			throw new ApplicationException(ErrorTypes.GENERAL_ERROR, "invalid Email or password");
		}

		UserLoginData userLoginData;

		if (user.getType().equals(UserType.COMPANY)) {
			userLoginData = new UserLoginData(user.getId(), user.getType(), user.getCompany().getCompanyId());
		}

		else {
			userLoginData = new UserLoginData(user.getId(), user.getType());

		}

		int token = generateToken(userLoginDetails);

		cacheController.put(String.valueOf(token), userLoginData);

		return new SuccessfulLoginData(token, userLoginData.getUserType());
	}

	private int generateToken(UserLoginDetails userLoginDetails) {
		String text = userLoginDetails.getEmail() + Calendar.getInstance().getTime().toString() + ENCRYPTION_TOKEN_SALT;
		return text.hashCode();
	}

	public boolean isUserExistsById(long id) throws ApplicationException {

		try {
			return this.userDao.existsById(id);

		} catch (Exception e) {
			throw new ApplicationException(ErrorTypes.INVALID_USER_ID, "please enter a valid id");
		}
	}



	public void deleteUser(long id) throws ApplicationException {
		try {

			if (!this.isUserExistsById(id)) {
				throw new Exception();
			}

			this.userDao.deleteById(id);

		} catch (Exception e) {
			throw new ApplicationException(ErrorTypes.INVALID_USER_ID, "please enter a valid id");
		}


	}
	
	public Long createUserByAdmin(User user) throws ApplicationException {
		// TODO Auto-generated method stub
		String repeatPassword = user.getPassword();
		
		validateCreateUser(user, repeatPassword);
		
		try {

			this.userDao.save(user);
			long id = user.getId();
			return id;
		} catch (Exception e) {

			throw new ApplicationException(ErrorTypes.FAIL_TO_CREATE_USER, "failed to create user");
		}
	}



	public long createUser(User user, String repeatPassword) throws ApplicationException {

		validateCreateUser(user, repeatPassword);


		try {

			this.userDao.save(user);
			long id = user.getId();
			return id;
		} catch (Exception e) {

			throw new ApplicationException(ErrorTypes.FAIL_TO_CREATE_USER, "failed to create user");
		}
	}

	  void validateCreateUser(User user, String repeatPassword) throws ApplicationException {


		if (this.userDao.existsByEmail(user.getEmail())) {

			throw new ApplicationException(ErrorTypes.EMAIL_IS_TAKEN, "Email is already in user, please choose another");
		}


		boolean isEmailValid = EmailValidator(user.getEmail());
		if (isEmailValid == false) {
			throw new ApplicationException(ErrorTypes.INVALID_EMAIL_ADDRESS, "invalid email addresss");
		}
		
		if (!user.getPassword().equals(repeatPassword)) {
			throw new ApplicationException(ErrorTypes.PASSWORD_MISSMATCH, "passwords mismatch");
		}

		if (user.getPassword().length()<6) {
			throw new ApplicationException(ErrorTypes.PASSWORD_TOO_SHORT ,"Password is too short");
		}

		if (user.getPassword().length()>12) {
			throw new ApplicationException(ErrorTypes.PASSWORD_TOO_LONG, "Password is too long");
		}

		boolean isHasCapitalLetter = false;
		int index = 0;
		while(index < user.getPassword().length() && !isHasCapitalLetter) {
			if (user.getPassword().charAt(index)>='A' && user.getPassword().charAt(index)<='Z') {
				isHasCapitalLetter = true;
			}
			index++;
		}

		if (!isHasCapitalLetter) {
			throw new ApplicationException(ErrorTypes.PASSWORD_NO_CAPITAL, "The password does not contain a capital letter");
		}

		boolean isHasLowerCaseLetter = false;
		index = 0;
		while(index < user.getPassword().length() && !isHasLowerCaseLetter) {
			if (user.getPassword().charAt(index)>='a' && user.getPassword().charAt(index)<='z') {
				isHasLowerCaseLetter = true;
			}
			index++;
		}

		if (!isHasLowerCaseLetter) {
			throw new ApplicationException(ErrorTypes.PASSWORD_NO_LOWER, "The password does not contain a lower case letter");
		}

	}




	private boolean EmailValidator(String email) throws ApplicationException {
		String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
		Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();

	}


	public List<User> getAllUsers () throws ApplicationException {
		try {

			return (List<User>) userDao.findAll();

		} catch (Exception e) {
			throw new ApplicationException(ErrorTypes.GENERAL_ERROR, "Failed to get all users, please try again later...");
		}
	}

	public Optional<User> getUserDetails(long id) throws ApplicationException {
		try {

			if (!userDao.existsById(id)) {
				throw new Exception();
			}

		} catch (Exception e) {

			throw new ApplicationException(ErrorTypes.INVALID_USER, "User doesn't exists");

		}
		
		try {
			return userDao.findById(id);
			
		} catch (Exception e) {
			throw new ApplicationException(ErrorTypes.FAIL_TO_GET_USERS, "failed to get users");
		}
	} 





	public List<User> getUserByType(UserType type) throws ApplicationException {
		try {

			if (type != UserType.ADMIN && 
				type != UserType.COMPANY && 
				type != UserType.CUSTOMER) {
				
			}

		} catch (Exception e) {
			throw new ApplicationException(ErrorTypes.INVALID_USERTYPE, "Invalid user type");
		}
		
		try {
			
			return userDao.getUserByType(type);
		} catch (Exception e) {
			throw new ApplicationException(ErrorTypes.FAIL_TO_GET_USERS, "failed to get users");
		}
		
	}


	public List<User> getUserByEmail (String email) throws ApplicationException {
		try {

			if (!EmailValidator(email)) {
				
				throw new Exception();
			}

		} catch (Exception e) {
			throw new ApplicationException(ErrorTypes.INVALID_EMAIL_ADDRESS, "please enter a valid email address");
		}
		
		try {
			
			if (userDao.existsByEmail(email) == false) {
				
				throw new Exception();
				
			}
			
		} catch (Exception e) {
			throw new ApplicationException(ErrorTypes.EMAIL_DOES_NOT_EXISTS, "there is no such email, please try another");
		}
		
		
		try {
			return userDao.findByEmail(email);
			
		} catch (Exception e) {

			throw new ApplicationException(ErrorTypes.GENERAL_ERROR, "general error please try again later");
		}
		
	}

	/**
	 * @return the userDbdao
	 */
	public IUserDao getUserDbdao() {
		return userDao;
	}

	/**
	 * @param userDao the userDbdao to set
	 */
	public void setUserDbdao(IUserDao userDao) {
		this.userDao = userDao;
	}

	


}
