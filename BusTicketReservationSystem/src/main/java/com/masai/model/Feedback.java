package com.masai.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.PastOrPresent;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Feedback {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer feedbackId;
	
	@Min(value = 1, message = "Rating should be between 1 to 10")
	@Max(value = 10, message = "Rating should be between 1 to 10")
	private Integer driverRating;
	
	@Min(value = 1, message = "Rating should be between 1 to 10")
	@Max(value = 10, message = "Rating should be between 1 to 10")
	private Integer serviceRating;
	
	@Min(value = 1, message = "Rating should be between 1 to 10")
	@Max(value = 10, message = "Rating should be between 1 to 10")
	private Integer overallRating;
	
	private String comments;
	
	@JsonFormat(pattern = "yyyy-MM-dd", shape = Shape.STRING)
	@PastOrPresent(message = "Date should be current date or past date")
	private LocalDate feedbackDate;
	
	@JsonIgnore
	@OneToOne
	private User user;
	
	@JsonIgnore
	@OneToOne
	private Bus bus;

	@Override
	public String toString() {
		return "Feedback [feedbackId=" + feedbackId + ", driverRating=" + driverRating + ", serviceRating="
				+ serviceRating + ", overallRating=" + overallRating + ", comments=" + comments + ", feedbackDate="
				+ feedbackDate + "]";
	}
	
}
