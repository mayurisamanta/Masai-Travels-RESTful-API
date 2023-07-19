package com.masai.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;


import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "user_account")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer userLoginId;
	
	private String userName;
	
	@Size(min = 6, message="Password length must be between 6 to 10 character")
	@Size(max = 10, message = "Password length must be between 6 to 10 character")
	private String password;

	private String firstName;
	
	private String lastName;
	
	private Long contact;
	
	@Email(message = "Please enter valid email Id")
	private String email;
	
	@JsonIgnore
	@OneToOne
	private Reservation reservation;

}
