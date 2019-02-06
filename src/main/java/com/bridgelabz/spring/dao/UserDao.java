package com.bridgelabz.spring.dao;
import javax.servlet.http.HttpServletResponse;

import com.bridgelabz.spring.model.UserDetails;

public interface UserDao {

	public int register(UserDetails user);

	public UserDetails loginUser(String emailId,HttpServletResponse resp);

	public UserDetails getUserById(int id);

	public void updateUser(int id, UserDetails user);

	public void deleteUser(int id);
}

