package com.bridgelabz.spring.service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bridgelabz.spring.model.UserDetails;

public interface UserService {
	 boolean register(UserDetails user, HttpServletRequest request);

	 UserDetails loginUser(String emailId, String password, HttpServletRequest request,HttpServletResponse resp);

	 UserDetails updateUser( String token, UserDetails user, HttpServletRequest request);

	 UserDetails deleteUser(int id, HttpServletRequest request);

	UserDetails activateUser(String token, HttpServletRequest request);
	
    UserDetails getUserByEmail( String userToken,HttpServletRequest request,UserDetails newPassword,
    		HttpServletResponse resp);
}

