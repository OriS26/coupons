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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ori.controller.UserController;
import com.ori.entities.Company;
import com.ori.entities.Purchase;
import com.ori.entities.SuccessfulLoginData;
import com.ori.entities.User;
import com.ori.entities.UserLoginDetails;
import com.ori.entities.UserRegisterDetails;
import com.ori.enums.UserType;
import com.ori.exceptions.ApplicationException;



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
	@PostMapping
	public Long createUser(@RequestBody UserRegisterDetails userRegisterDetails) throws ApplicationException {
		String repeatedPassword = userRegisterDetails.getRepeatPassword();
		
		User user = new User();
		
		
		
		 return this.userController.createUser(user, repeatedPassword);
	}
	
	@PostMapping("/adminCreateUser")
	
	public Long createUserByAdmin(@RequestBody User user) throws ApplicationException {
		
		return userController.createUserByAdmin(user);
	}
	
	
	@PutMapping("/adminEditUser")
	
	public void editUserByAdmin(@RequestBody User user) throws ApplicationException {
		
		
		userController.createUserByAdmin(user);
	}

	//  URL : http://localhost:8080/users
	@PutMapping
	public void updateUser(@RequestBody UserRegisterDetails userRegisterDetails) throws ApplicationException {
		
		String repeatedPassword = userRegisterDetails.getRepeatPassword();
		
		User user = new User();
		
		this.userController.createUser(user, repeatedPassword);
		System.out.println(user);
	}

	// http://localhost:8080/users/12
	@GetMapping("{id}")
	public Optional<User> getUserDetails (@PathVariable("id") long id) throws ApplicationException {
		System.out.println(id);

		
		return this.userController.getUserDetails(id);
	}

	// http://localhost:8080/users/12
	@DeleteMapping("{userId}")
	public void deleteUser(@PathVariable("userId") long id) throws ApplicationException {
		
		this.userController.deleteUser(id);
		System.out.println(id + " has been deleted");
	}


	//  URL : http://localhost:8080/users
	@GetMapping
	public List<User> getAllUsers() throws ApplicationException{

		
		return this.userController.getAllUsers();
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
