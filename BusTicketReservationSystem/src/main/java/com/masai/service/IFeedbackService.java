package com.masai.service;

import java.util.List;

import com.masai.exception.BusException;
import com.masai.exception.FeedbackException;
import com.masai.exception.UserException;
import com.masai.model.Feedback;

public interface IFeedbackService {

	public Feedback addFeedback(Integer userLoginId, Integer busId, Feedback feedback) throws FeedbackException, UserException, BusException;
	
	public Feedback updateFeedback(Integer feedbackId,Feedback feedback) throws FeedbackException;
	
	public Feedback viewFeedback(Integer feedbackId) throws FeedbackException;
	
	public List<Feedback> viewAllFeedback() throws FeedbackException;
}
