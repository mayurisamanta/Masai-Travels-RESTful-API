package com.masai.service;

import com.masai.exception.LoginException;
import com.masai.model.AdminDto;

public interface AdminLoginService {
	public String logIntoAccount(AdminDto dto) throws LoginException;
	public String logOutFromAccount(String key) throws LoginException;
}