package com.bridgelabz.spring.dao;
import javax.servlet.http.HttpServletResponse;

import com.bridgelabz.spring.model.UserDetails;

public interface UserDao {

	 int register(UserDetails user);

	 UserDetails loginUser(String emailId,HttpServletResponse resp);

	 UserDetails getUserById(int id);

	 void updateUser(String token, UserDetails user);

	 void deleteUser(int id);
	
	 UserDetails getUserByEmailId(String emailId);
	  
}

