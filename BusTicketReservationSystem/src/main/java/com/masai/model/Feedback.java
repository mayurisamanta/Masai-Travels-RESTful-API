package com.masai.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Feedback {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer feedbackId;
	
	private Integer driverRating;
	
	private Integer serviceRating;
	
	private Integer overallRating;
	
	private String comments;
	
	private LocalDate feedbackDate;

	public Feedback() {
		super();
	}

	public Feedback(Integer feedbackId, Integer driverRating, Integer serviceRating, Integer overallRating,
			String comments, LocalDate feedbackDate, User user, Bus bus) {
		super();
		this.feedbackId = feedbackId;
		this.driverRating = driverRating;
		this.serviceRating = serviceRating;
		this.overallRating = overallRating;
		this.comments = comments;
		this.feedbackDate = feedbackDate;
		this.user = user;
		this.bus = bus;
	}

	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
	private User user;
	
	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
	private Bus bus;

	public Integer getFeedbackId() {
		return feedbackId;
	}

	public void setFeedbackId(Integer feedbackId) {
		this.feedbackId = feedbackId;
	}

	public Integer getDriverRating() {
		return driverRating;
	}

	public void setDriverRating(Integer driverRating) {
		this.driverRating = driverRating;
	}

	public Integer getServiceRating() {
		return serviceRating;
	}

	public void setServiceRating(Integer serviceRating) {
		this.serviceRating = serviceRating;
	}

	public Integer getOverallRating() {
		return overallRating;
	}

	public void setOverallRating(Integer overallRating) {
		this.overallRating = overallRating;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public LocalDate getFeedbackDate() {
		return feedbackDate;
	}

	public void setFeedbackDate(LocalDate feedbackDate) {
		this.feedbackDate = feedbackDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Bus getBus() {
		return bus;
	}

	public void setBus(Bus bus) {
		this.bus = bus;
	}

	@Override
	public String toString() {
		return "Feedback [feedbackId=" + feedbackId + ", driverRating=" + driverRating + ", serviceRating="
				+ serviceRating + ", overallRating=" + overallRating + ", comments=" + comments + ", feedbackDate="
				+ feedbackDate + "]";
	}
	
}
