package com.bridgelabz.spring.service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bridgelabz.spring.dao.UserDao;
import com.bridgelabz.spring.model.UserDetails;
import com.bridgelabz.spring.utility.EmailUtility;
import com.bridgelabz.spring.utility.TokenGenerator;

@Service
public class UserServiceImlp implements UserService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private TokenGenerator tokenGenerator;

	@Autowired
	private PasswordEncoder bcryptEncoder;
	@Autowired
	private EmailUtility emailUtilility;


    @Transactional
    public boolean register(UserDetails user, HttpServletRequest request) {
        user.setPassword(bcryptEncoder.encode(user.getPassword()));
        int id = userDao.register(user);
        if (id > 0) {
            String token = tokenGenerator.generateToken(String.valueOf(id));
            System.out.println(token);
            String verificationLink= "http://localhost:8080/FundooN1/activationstatus/"+token;
            emailUtilility.sendEmail("", "Hi, click the link below to verify your email ", verificationLink);
            return true;
        }
        return false;
    }

    @Transactional
	public UserDetails loginUser(String emailId, String password, HttpServletRequest request,HttpServletResponse resp) {

		UserDetails details = userDao.loginUser(emailId,resp);
		if (details != null) {
			boolean match=bcryptEncoder.matches(password, details.getPassword());
			if(match)
				return details;
		}
		return null;

	}
    @Transactional
	public UserDetails updateUser(String token, UserDetails user, HttpServletRequest request)
    {
    	int id =tokenGenerator.VerifyToken(token);
		UserDetails user2 = userDao.getUserById(id);
		if (user2 != null) {
			user2.setEmailId(user.getEmailId());
			user2.setMobileNumber(user.getMobileNumber());
			user2.setName(user.getName());
			user2.setPassword(user.getPassword());
			userDao.updateUser(token, user2);
		}
		return user2;
	}
    @Transactional
	public UserDetails deleteUser(int id, HttpServletRequest request) {
		UserDetails user2 = userDao.getUserById(id);
		if (user2 != null) {
			userDao.deleteUser(id);
		}
		return user2;

	}
    @Transactional
	 public UserDetails activateUser(String token, HttpServletRequest request) {
	        int id=tokenGenerator.VerifyToken(token);
	        UserDetails user=userDao.getUserById(id);
	        if(user!=null)
	        {
	            user.setActivate_status(true);
	            userDao.updateUser(token, user);
	        }
	        return user;
	    }
    
    @Transactional
    public UserDetails getUserByEmail( String userToken,HttpServletRequest request,UserDetails newPassword,
    		HttpServletResponse resp) {
        UserDetails exsistingUser=userDao.getUserByEmailId(newPassword.getEmailId());
        if(exsistingUser!=null)
        {
            //String PasswordResetLink =    tokenGenerator.generateUrl("/resetpassword/", exsistingUser, request, resp);
            String token = tokenGenerator.generateToken(String.valueOf(exsistingUser.getId()));
            StringBuffer url=request.getRequestURL();
            String url2=url.substring(0, url.lastIndexOf("/"));
            url2=url2+""+token;
            System.out.println(token);
            emailUtilility.sendEmail("ashwinitikotekar@gmail.com", "Password Reset Link Mail", "please click on this link to reset password "+url);
        }
        exsistingUser.setEmailId("");
        return exsistingUser;
    }

    
}


