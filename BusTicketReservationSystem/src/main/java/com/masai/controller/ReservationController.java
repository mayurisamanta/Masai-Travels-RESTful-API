package com.masai.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exception.ReservationException;
import com.masai.model.Reservation;
import com.masai.service.ReservationService;

//import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class ReservationController {

	@Autowired
	private ReservationService rService;
	
	
	
	@PostMapping("/reservations")
	public ResponseEntity<Reservation> addNewReservationHandler(@RequestBody Reservation reservation) throws ReservationException{
		
		Reservation saveReservation = rService.addNewReservation(reservation);
		
		return new ResponseEntity<Reservation>(saveReservation, HttpStatus.CREATED);
	}
	
	
	@PutMapping("/reservations")
	public ResponseEntity<Reservation> updateReservationHandler(@RequestBody Reservation reservation) throws ReservationException{
		
		Reservation updateReservation  = rService.updateReservation(reservation);
		
		return new ResponseEntity<Reservation>(updateReservation, HttpStatus.ACCEPTED);
		
	}
	
	
}
