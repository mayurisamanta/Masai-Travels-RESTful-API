package com.masai.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exception.LoginException;
import com.masai.model.LoginDTO;
import com.masai.service.LoginService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/user")
public class UserLoginController {
	@Autowired
	private LoginService lService;
	
	@PostMapping("/login")
	public ResponseEntity<String> userLoginHandler(@Valid @RequestBody LoginDTO dto) throws LoginException{
		String msg=lService.logIntoAccount(dto);
		return new ResponseEntity<String>(msg,HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/logout")
	public ResponseEntity<String> userLogoutHandler(@RequestParam String key) throws LoginException{
		String msg=lService.logOutFromAccount(key);
		return new ResponseEntity<String>(msg,HttpStatus.ACCEPTED);
	}
}

