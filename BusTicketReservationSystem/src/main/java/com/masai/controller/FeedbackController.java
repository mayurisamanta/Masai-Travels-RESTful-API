package com.masai.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exception.BusException;
import com.masai.exception.FeedbackException;
import com.masai.exception.UserException;
import com.masai.model.Feedback;
import com.masai.service.IFeedbackServiceImpl;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

	@Autowired
	private IFeedbackServiceImpl fservice;
	
	@PostMapping("/add/{userLoginId}/{busId}")
    public ResponseEntity<Feedback> addRouteHandler(@PathVariable("userLoginId") Integer userLoginId, @PathVariable("busId") Integer busId,@Valid @RequestBody Feedback feedback) throws FeedbackException, UserException, BusException {
		
		Feedback f = fservice.addFeedback(userLoginId, busId, feedback);
		
		return new ResponseEntity<Feedback>(f, HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{feedbackId}")
	public ResponseEntity<Feedback> updateRoute(@PathVariable("feedbackId") Integer feedbackId, @Valid @RequestBody Feedback feedback) throws FeedbackException{
		
		Feedback f = fservice.updateFeedback(feedbackId ,feedback);
		
		return new ResponseEntity<Feedback>(f, HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/view/{feedbackId}")
	public ResponseEntity<Feedback> viewRoute(@PathVariable("feedbackId") Integer feedbackId) throws FeedbackException{
		
		Feedback f = fservice.viewFeedback(feedbackId);
		
		return new ResponseEntity<Feedback>(f, HttpStatus.FOUND);
	}
	
	@GetMapping("/viewAll")
	public ResponseEntity<List<Feedback>> viewAllRoute() throws FeedbackException{
		
		List<Feedback> f = fservice.viewAllFeedback();
		
		return new ResponseEntity<List<Feedback>>(f, HttpStatus.FOUND);
	}
	
}
