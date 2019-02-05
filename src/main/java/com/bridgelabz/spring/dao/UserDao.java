package com.bridgelabz.spring.dao;
import com.bridgelabz.spring.model.UserDetails;

public interface UserDao {

	public int register(UserDetails user);

	public UserDetails loginUser(String emailId);

	public UserDetails getUserById(int id);

	public void updateUser(int id, UserDetails user);

	public void deleteUser(int id);
}

