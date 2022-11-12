package com.masai.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.Data;
import com.masai.exception.UserException;
import com.masai.model.CurrentUserSession;
import com.masai.model.User;
import com.masai.repository.SessionRepo;
import com.masai.repository.UserRepo;

@Service
public class IUserServiceImpl implements IUserService{
	
	@Autowired
	private UserRepo uRepo;
	
	@Autowired
	private SessionRepo srepo;
	
	@Override
	public User addUser(User user) throws UserException {
		User u= uRepo.findByUserName(user.getUserName());
		if(u!=null) {
			throw new UserException("User already exist with this username.");
		}
		return uRepo.save(user);
	}

	@Override
	public User updateUser(User user,String key) throws UserException {
		CurrentUserSession loggedInUser=srepo.findByUuid(key);
		if(loggedInUser==null) {
			throw new UserException("Please provide a valid key to update user.");
		}
		if(user.getUserLoginId()==loggedInUser.getUserId()) {
			return uRepo.save(user);
		}
		else {
			throw new UserException("Invalid User details, please login first");
		}
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
