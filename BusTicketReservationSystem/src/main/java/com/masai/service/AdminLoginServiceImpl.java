package com.masai.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.LoginException;
import com.masai.model.Admin;
import com.masai.model.AdminDto;
import com.masai.model.CurrentUserSession;
import com.masai.repository.SessionRepo;

import net.bytebuddy.utility.RandomString;

@Service
public class AdminLoginServiceImpl implements AdminLoginService{

	@Autowired
	private SessionRepo sRepo;
	
	@Override
	public String logIntoAccount(AdminDto dto) throws LoginException {
		// TODO Auto-generated method stub
		Admin adm=new Admin();
		if(!adm.username.equalsIgnoreCase(dto.getUserName())) {
			throw new LoginException("Please Enter a valid Username");
		}
		Optional<CurrentUserSession> validUserSessionOpt =sRepo.findById(adm.id);
		if(validUserSessionOpt.isPresent()) {
			throw new LoginException("Admin already Logged in with this Username");
		}
		if(adm.password.equals(dto.getPassword())) {
			String key=RandomString.make(6);
			CurrentUserSession currentUserSession=new CurrentUserSession(adm.id,"admin",key,LocalDateTime.now());
			sRepo.save(currentUserSession);
			return currentUserSession.toString();
		}else {
			throw new LoginException("Please Enter a valid Password");
		}
	}

	@Override
	public String logOutFromAccount(String key) throws LoginException {
		CurrentUserSession validUserSession=sRepo.findByUuid(key);
		
		if(validUserSession==null) {
			throw new LoginException("Admin not logged in with this Username.");
		}
		sRepo.delete(validUserSession);
		return "Logged out successfully";
	}

}