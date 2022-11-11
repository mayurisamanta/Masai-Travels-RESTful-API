package com.masai.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.FeedbackException;
import com.masai.model.Feedback;
import com.masai.repository.FeedbackDao;

@Service
public class IFeedbackServiceImpl implements IFeedbackService{

	@Autowired
	private FeedbackDao fdao;

	@Override
	public Feedback addFeedback(Feedback feedback) throws FeedbackException {
		Feedback f = fdao.save(feedback);
		return f;
	}

	@Override
	public Feedback updateFeedback(Feedback feedback) throws FeedbackException {
		Feedback f = fdao.findById(feedback.getFeedbackId()).orElseThrow(() -> new FeedbackException("Feedback with Id " + feedback.getFeedbackId() + " does not exist"));
		
		Feedback updated = fdao.save(feedback);
		
		return updated;
	}

	@Override
	public Feedback viewFeedback(Integer feedbackId) throws FeedbackException {
		Feedback f = fdao.findById(feedbackId).orElseThrow(() -> new FeedbackException("Feedback with Id " + feedbackId + " does not exist"));
		return f;
	}

	@Override
	public List<Feedback> viewAllFeedback() throws FeedbackException {
		List<Feedback> f= fdao.findAll();
		
		if (!f.isEmpty()) return f;
		else throw new FeedbackException("Feedback not found");
	}
	
	
}
