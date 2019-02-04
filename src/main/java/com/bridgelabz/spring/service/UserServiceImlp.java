package com.bridgelabz.spring.service;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bridgelabz.spring.dao.UserDao;
import com.bridgelabz.spring.model.UserDetails;
import com.bridgelabz.spring.utility.TokenGenerator;




@Service
public class UserServiceImlp implements UserService {

	@Autowired
    private UserDao userDao;
    @Autowired
    private TokenGenerator tokenGenerator;

    @Transactional
    public boolean register(UserDetails user, HttpServletRequest request) {
        int id = userDao.register(user);
        if (id > 0) {
            String token = tokenGenerator.generateToken(String.valueOf(id));
            System.out.println(token);
            return true;
        }
        return false;
    }

    public UserDetails loginUser(String emailId, String password, HttpServletRequest request) {
        return userDao.loginUser(emailId, password);
    }

    public UserDetails updateUser(int id, UserDetails user, HttpServletRequest request) {
    	UserDetails user2 = userDao.getUserById(id);
        if (user2 != null) {
            user2.setEmailId(user.getEmailId());
            user2.setMobileNumber(user.getMobileNumber());
            user2.setName(user.getName());
            user2.setPassword(user.getPassword());
            userDao.updateUser(id, user2);
        }
        return user2;
    }

    public UserDetails deleteUser(int id, HttpServletRequest request) {
    	UserDetails user2 = userDao.getUserById(id);
        if (user2 != null) {
            userDao.deleteUser(id);
        }
        return user2;
    
}

}


