package com.ori.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

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

import com.ori.controller.CacheController;
import com.ori.controller.UserController;
import com.ori.data.UserLoginData;
import com.ori.entities.Company;
import com.ori.entities.Purchase;
import com.ori.entities.SuccessfulLoginData;
import com.ori.entities.User;
import com.ori.entities.UserLoginDetails;
import com.ori.entities.UserRegisterDetails;
import com.ori.enums.ErrorTypes;
import com.ori.enums.UserType;
import com.ori.exceptions.ApplicationException;

import javafx.scene.chart.PieChart.Data;



@RestController
@RequestMapping("/users")
public class UserApi {

	@Autowired
	private UserController userController;

	

	//  URL : http://localhost:8080/users/login
	@PostMapping("/login")
	public SuccessfulLoginData login(@RequestBody UserLoginDetails userLoginDetails ) throws ApplicationException {
		return userController.login(userLoginDetails);
	}
	// ---------------------------
	// CRUD
	// -------------------------

	//  URL : http://localhost:8080/users
	
	
	@GetMapping("/isAdmin")
	public boolean isAdmin(HttpServletRequest request) throws ApplicationException {
		
		UserLoginData userLoginData = (UserLoginData) request.getAttribute("userLoginData");
		
         if(userLoginData.getUserType() != UserType.ADMIN) {
			
			throw new ApplicationException(ErrorTypes.UNAUTHROIZED, "UNAUTHORIZED");
		}
         
         return true;
		
	}
	
	@GetMapping("/isCompany")
	public boolean isCompany(HttpServletRequest request) throws ApplicationException {
		
		UserLoginData userLoginData = (UserLoginData) request.getAttribute("userLoginData");
		
         if(userLoginData.getUserType() != UserType.COMPANY) {
			
			throw new ApplicationException(ErrorTypes.UNAUTHROIZED, "UNAUTHORIZED");
		}
         
         return true;
		
	}
	
	
	@PutMapping("/updateMyCompanyUser")
	public void updateMyCompanyUser(@RequestBody User user, HttpServletRequest request) throws ApplicationException {
		
		UserLoginData userLoginData = (UserLoginData) request.getAttribute("userLoginData");
		
		user.setId(userLoginData.getId());
		
		this.userController.updateMyCompanyUser(user);
	}
	
	
	@PostMapping
	public Long createUser(@RequestBody User user) throws ApplicationException {
		
		if(user.getNewPassword().equals(null)) {
			
			return this.userController.createUser(user);
		}
		
		else {
			
			return this.userController.updateUser(user);
			 
			
		}
		
		
		
	}
	
	@PostMapping("/adminCreateUser")
	
	public Long createUserByAdmin(@RequestBody User user, HttpServletRequest request) throws ApplicationException {
		
		UserLoginData userLoginData = (UserLoginData) request.getAttribute("userLoginData");
		
		if(userLoginData.getUserType() != UserType.ADMIN) {
			
			throw new ApplicationException(ErrorTypes.UNAUTHROIZED, "UNAUTHORIZED");
		}
		
		return userController.createUserByAdmin(user);
	}
	
	
	@PutMapping("/adminEditUser")
	
	public void editUserByAdmin(@RequestBody User user, HttpServletRequest request) throws ApplicationException {
		
		UserLoginData userLoginData = (UserLoginData) request.getAttribute("userLoginData");
		
		if(userLoginData.getUserType() != UserType.ADMIN) {
			throw new ApplicationException(ErrorTypes.UNAUTHROIZED, "UNAUTHORIZED");
		}
		
		userController.createUserByAdmin(user);
	}

	//  URL : http://localhost:8080/users
	@PutMapping
	public void updateUser(@RequestBody User user) throws ApplicationException {
		
		
		
		this.userController.createUser(user);
		System.out.println(user);
	}

	// http://localhost:8080/users/12
	@GetMapping("{id}")
	public User getUserDetails (@PathVariable("id") long id, HttpServletRequest request) throws ApplicationException {
		System.out.println(id);
		
		UserLoginData userLoginData = (UserLoginData) request.getAttribute("userLoginData");
		
		if(userLoginData.getUserType() != UserType.ADMIN) {
			throw new ApplicationException(ErrorTypes.UNAUTHROIZED, "UNAUTHORIZED");
		}

		
		return this.userController.getUserDetails(id);
	}

	// http://localhost:8080/users/12
	@DeleteMapping("{userId}")
	public void deleteUser(@PathVariable("userId") long id, HttpServletRequest request ) throws ApplicationException {
		
		UserLoginData userLoginData = (UserLoginData) request.getAttribute("userLoginData");
		
		if(userLoginData.getUserType() != UserType.ADMIN) {
			throw new ApplicationException(ErrorTypes.UNAUTHROIZED, "UNAUTHORIZED");
		}
		
		this.userController.deleteUser(id);
		System.out.println(id + " has been deleted");
	}


	//  URL : http://localhost:8080/users
	@GetMapping
	public List<User> getAllUsers(HttpServletRequest request) throws ApplicationException{
		UserLoginData userLoginData = (UserLoginData) request.getAttribute("userLoginData");
		
		if(userLoginData.getUserType() != UserType.ADMIN) {
			throw new ApplicationException(ErrorTypes.UNAUTHROIZED, "UNAUTHORIZED");
		}
		return this.userController.getAllUsers();
	}
	
	
	@GetMapping("/getMyDetails")
	public User getMyUserDetails (HttpServletRequest request) throws ApplicationException {
		
		UserLoginData userLoginData = (UserLoginData) request.getAttribute("userLoginData");
		
		long userId = userLoginData.getId();
		
		return this.userController.getUserDetails(userId);
		
		
	}

	@GetMapping("/ByEmail")
	public List<User> getUserByEmail (@RequestParam("email") String email) throws ApplicationException {
		
		return this.userController.getUserByEmail(email);
	}
	
	@GetMapping("/ByCategory")
	public List<User> getUserByType (@RequestParam("category") UserType type) throws ApplicationException {
		
		return this.userController.getUserByType(type);
	}
	


}
