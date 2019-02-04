package com.bridgelabz.spring.controller;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.bridgelabz.spring.model.UserDetails;
import com.bridgelabz.spring.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<Void> registerUser(@RequestBody UserDetails user, HttpServletRequest request) {
		try {
			if (userService.register(user, request))
				return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<Void>(HttpStatus.CONFLICT);
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ResponseEntity<?> loginUser(@RequestParam("emailId") String emailId,
			@RequestParam("password") String password, HttpServletRequest request) {

		UserDetails user = userService.loginUser(emailId, password, request);
		if (user != null) {
			return new ResponseEntity<UserDetails>(user, HttpStatus.FOUND);
		} else {
			return new ResponseEntity<String>("Incorrect emailId or password", HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ResponseEntity<?> updateUser(@RequestParam("id") int id, @RequestBody UserDetails user,
			HttpServletRequest request) {

		UserDetails user2 = userService.updateUser(id, user, request);
		if (user2 != null) {
			return new ResponseEntity<UserDetails>(user2, HttpStatus.FOUND);
		} else {
			return new ResponseEntity<String>("Email incorrect. Please enter valid email address present in database",
					HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteUser(@RequestParam("id") int id, HttpServletRequest request) {

		UserDetails user = userService.deleteUser(id, request);
		if (user != null) {
			return new ResponseEntity<UserDetails>(user, HttpStatus.FOUND);
		} else {
			return new ResponseEntity<String>("Email incorrect. Please enter valid email address present in database",
					HttpStatus.NOT_FOUND);
		}
	}

}

