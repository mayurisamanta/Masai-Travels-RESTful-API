package com.masai.service;

import java.util.List;

import com.masai.exception.BusException;
import com.masai.exception.FeedbackException;
import com.masai.exception.UserException;
import com.masai.model.Feedback;

public interface IFeedbackService {

	public Feedback addFeedback( Integer busId, Feedback feedback,String key) throws FeedbackException, UserException, BusException;
	
	public Feedback updateFeedback(Feedback feedback,String key) throws FeedbackException,UserException;
	
	public Feedback viewFeedback(Integer feedbackId,String key) throws FeedbackException,UserException;
	
	public List<Feedback> viewAllFeedback(String key) throws FeedbackException,UserException;
}
