package com.masai.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	
	@ExceptionHandler(RouteException.class)
	public ResponseEntity<MyErrorDetails> myRouteException(RouteException re, WebRequest webReq){
		
		MyErrorDetails red = new MyErrorDetails();
		red.setTimestamp(LocalDateTime.now());
		red.setMessage(re.getMessage());
		red.setDetails(webReq.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(red, HttpStatus.BAD_REQUEST);
		
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<MyErrorDetails> otherExceptionHandler(Exception se, WebRequest req){
	
		MyErrorDetails err= new MyErrorDetails();
			err.setTimestamp(LocalDateTime.now());
			err.setMessage(se.getMessage());
			err.setDetails(req.getDescription(false));
				
		return new ResponseEntity<MyErrorDetails>(err, HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
}
