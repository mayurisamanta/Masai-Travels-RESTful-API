package com.masai.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.UserException;
import com.masai.model.User;
import com.masai.repository.UserRepo;

@Service
public class IUserServiceImpl implements IUserService{
	
	@Autowired
	private UserRepo uRepo;
	
	@Override
	public User addUser(User user) throws UserException {
		User u=uRepo.save(user);
		return u;
	}

	@Override
	public User updateUser(User user) throws UserException {
		User u=uRepo.findById(user.getUserLoginId())
				.orElseThrow(()-> new UserException("User with User Id "+user.getUserLoginId()+" does not exist"));
		return uRepo.save(user);
	}

	@Override
	public User deleteUser(Integer userId) throws UserException {
		User u=uRepo.findById(userId)
				.orElseThrow(()-> new UserException("User with User Id "+userId+" does not exist"));
		uRepo.delete(u);
		return u;
	}

	@Override
	public User viewUser(Integer userId) throws UserException {
		User u=uRepo.findById(userId)
				.orElseThrow(()-> new UserException("User with User Id "+userId+" does not exist"));
		return u;
	}

	@Override
	public List<User> viewAllUsers() throws UserException {
		List<User> users=uRepo.findAll();
		if(users.size()!=0) {
			return users;
		}else {
			throw new UserException("No User Found.");
		}
	}

}
