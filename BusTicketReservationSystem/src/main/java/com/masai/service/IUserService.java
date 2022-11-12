package com.masai.service;

import java.util.List;

import com.masai.exception.UserException;
import com.masai.model.User;

public interface IUserService {
	public User addUser(User user) throws UserException;
	public User updateUser(User user,String Key) throws UserException;
	public User deleteUser(Integer userId) throws UserException;
	public User viewUser(Integer userId) throws UserException;
	public List<User> viewAllUsers() throws UserException;
}
