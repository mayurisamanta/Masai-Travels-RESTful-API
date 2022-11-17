package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.Data;

import com.masai.exception.ReservationException;
import com.masai.exception.UserException;
import com.masai.model.CurrentUserSession;
import com.masai.model.Reservation;
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
			throw new UserException("Please provide a valid key to update user");
		}
		User curr=uRepo.findById(user.getUserLoginId())
				.orElseThrow(()-> new UserException("User with User Id "+user.getUserLoginId()+" does not exist"));
		if (loggedInUser.getType().equalsIgnoreCase("Admin")) {
			if (user.getContact() != null) curr.setContact(user.getContact());
			if (user.getEmail() != null) curr.setEmail(user.getEmail());
			if (user.getFirstName() != null) curr.setFirstName(user.getFirstName());
			if (user.getLastName() != null) curr.setLastName(user.getLastName());
			if (user.getPassword() != null) curr.setPassword(user.getPassword());
			if (user.getUserName() != null) curr.setUserName(user.getUserName());
			User saved = uRepo.save(curr);
			return saved;
		}
		if(user.getUserLoginId()==loggedInUser.getUserId()) {
			if (user.getContact() != null) curr.setContact(user.getContact());
			if (user.getEmail() != null) curr.setEmail(user.getEmail());
			if (user.getFirstName() != null) curr.setFirstName(user.getFirstName());
			if (user.getLastName() != null) curr.setLastName(user.getLastName());
			if (user.getPassword() != null) curr.setPassword(user.getPassword());
			if (user.getUserName() != null) curr.setUserName(user.getUserName());
			
			User saved = uRepo.save(curr);
			
			return saved;
			
		}
		else throw new UserException("Access denied.");
				
	}

	@Override
	public User deleteUser(Integer userId,String key) throws UserException {
		CurrentUserSession loggedInUser=srepo.findByUuid(key);
		if(loggedInUser==null) {
			throw new UserException("Please provide a valid key to delete user.");
		}
		User u=uRepo.findById(userId)
				.orElseThrow(()-> new UserException("User with User Id "+userId+" does not exist"));
		if (loggedInUser.getType().equalsIgnoreCase("Admin")) {
			uRepo.delete(u);
			return u;
		}
		if(u.getUserLoginId()==loggedInUser.getUserId()) {
			uRepo.delete(u);
			srepo.delete(loggedInUser);
			return u;
		}
		else {
			throw new UserException("Access denied.");
		}
		
	}

	@Override
	public User viewUser(Integer userId,String key) throws UserException {
		CurrentUserSession loggedInUser=srepo.findByUuid(key);
		if(loggedInUser==null) {
			throw new UserException("Please provide a valid key to view user.");
		}
		if (loggedInUser.getType().equalsIgnoreCase("Admin")) {
			
			User u=uRepo.findById(userId)
					.orElseThrow(()-> new UserException("User with User Id "+userId+" does not exist"));
			
			return u;
		}
		else if (loggedInUser.getType().equalsIgnoreCase("user")){
			User u=uRepo.findById(userId)
					.orElseThrow(()-> new UserException("User with User Id "+userId+" does not exist"));
			if(u.getUserLoginId()==loggedInUser.getUserId()) {
				return u;
			}
			else {
				throw new UserException("Invalid User details, please login first");
			}
		}
		else throw new UserException("Access denied");
		
	}

	@Override
	public List<User> viewAllUsers(String key) throws UserException {
		CurrentUserSession loggedInUser=srepo.findByUuid(key);
		if(loggedInUser==null) {
			throw new UserException("Please provide a valid key to delete user.");
		}
		if (loggedInUser.getType().equalsIgnoreCase("admin")) {
			
			List<User> users=uRepo.findAll();
			if(users.size()!=0) {
				return users;
			}else {
				throw new UserException("No User Found.");
			}
		}
		else throw new UserException("Access denied");
	}

}