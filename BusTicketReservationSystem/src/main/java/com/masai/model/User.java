package com.masai.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer userLoginId;
	
//	@NotNull(message = "Username cannot be null.")
	private String userName;
	
//	@NotNull(message = "Password cannot be null.")
//	@NotBlank(message = "Password cannot be empty.")
	@Size(min=6,message="Password must be greater than 6 character.")
	private String password;

//	@NotNull(message = "FirstName cannot be null.")
//	@NotBlank(message = "FirstName cannot be empty.")
	private String firstName;
	
//	@NotNull(message = "LastName cannot be null.")
//	@NotBlank(message = "LastName cannot be empty.")
	private String lastName;
	
//	@NotNull(message = "Contact cannot be null.")
	private Long contact;
	
//	@NotNull(message = "Email cannot be null.")
	@Email
	private String email;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Reservation reservation;
}
