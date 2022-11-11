package com.masai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exception.UserException;
import com.masai.model.User;
import com.masai.service.IUserService;

@RestController
public class UserController {
	
	@Autowired
	private IUserService uService;

	@PostMapping("/user")
	public ResponseEntity<User> addUser(@RequestBody User user) throws UserException {
		User u=uService.addUser(user);
		return new ResponseEntity<User>(u,HttpStatus.CREATED);
	}
	
	@PostMapping("/user/update")
	public ResponseEntity<User> updateUser(@RequestBody User user) throws UserException {
		User u=uService.updateUser(user);
		return new ResponseEntity<User>(u,HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("user/delete/{userId}")
	public ResponseEntity<User> deleteUser(@PathVariable("userId") Integer userId) throws UserException {
		User u=uService.deleteUser(userId);
		return new ResponseEntity<User>(u,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/user/view/{userId}")
	public ResponseEntity<User> viewUser(@PathVariable("userId") Integer userId) throws UserException {
		User u=uService.viewUser(userId);
		return new ResponseEntity<User>(u,HttpStatus.FOUND);
	}
	
	@GetMapping("/user/viewall")
	public ResponseEntity<List<User>> viewAllUsers() throws UserException {
		List<User> users=uService.viewAllUsers();
		return new ResponseEntity<List<User>>(users,HttpStatus.FOUND);
	}
}
