package com.bridgelabz.spring.service;
import javax.servlet.http.HttpServletRequest;
import com.bridgelabz.spring.model.UserDetails;

public interface UserService {
	public boolean register(UserDetails user, HttpServletRequest request);

	public UserDetails loginUser(String emailId, String password, HttpServletRequest request);

	public UserDetails updateUser(int id, UserDetails user, HttpServletRequest request);

	public UserDetails deleteUser(int id, HttpServletRequest request);

	UserDetails activateUser(String token, HttpServletRequest request);

}


