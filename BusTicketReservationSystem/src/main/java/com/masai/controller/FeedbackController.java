package com.masai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exception.FeedbackException;
import com.masai.model.Feedback;
import com.masai.service.IFeedbackServiceImpl;

@RestController
public class FeedbackController {

	@Autowired
	private IFeedbackServiceImpl fservice;
	
	@PostMapping("/addFeedback/{userLoginId}/{busId}")
    public ResponseEntity<Feedback> addRouteHandler(@PathVariable("userLoginId") Integer userLoginId, @PathVariable("busId") Integer busId, @RequestBody Feedback feedback) throws FeedbackException {
		
		Feedback f = fservice.addFeedback(userLoginId, busId, feedback);
		
		return new ResponseEntity<Feedback>(f, HttpStatus.OK);
	}
	
	@PutMapping("/updateFeedback")
	public ResponseEntity<Feedback> updateRoute(@RequestBody Feedback feedback) throws FeedbackException{
		
		Feedback f = fservice.updateFeedback(feedback);
		
		return new ResponseEntity<Feedback>(f, HttpStatus.OK);
		
	}
	
	@GetMapping("/getFeedback/{feedbackId}")
	public ResponseEntity<Feedback> viewRoute(@PathVariable("feedbackId") Integer feedbackId) throws FeedbackException{
		
		Feedback f = fservice.viewFeedback(feedbackId);
		
		return new ResponseEntity<Feedback>(f, HttpStatus.OK);
	}
	
	@GetMapping("/getAllRoute")
	public ResponseEntity<List<Feedback>> viewAllRoute() throws FeedbackException{
		
		List<Feedback> f = fservice.viewAllFeedback();
		
		return new ResponseEntity<List<Feedback>>(f, HttpStatus.OK);
	}
	
}
