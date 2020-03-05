package com.ori.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ori.data.UserLoginData;
import com.ori.entities.SuccessfulLoginData;
import com.ori.entities.User;
import com.ori.entities.UserLoginDetails;
import com.ori.enums.UserType;

public interface IUserDao extends CrudRepository<User, Long> {


	public List<User> findByEmail(String name);
	
	public User findByEmailAndPassword(String email, String password);

	public User findById(User user);

	public boolean existsByEmail(String email);

	public List<User> getUserByType(UserType type);
    
	}

	

	


