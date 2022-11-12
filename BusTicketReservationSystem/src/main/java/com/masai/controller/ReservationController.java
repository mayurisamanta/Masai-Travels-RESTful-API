package com.masai.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exception.BusException;
import com.masai.exception.ReservationException;
import com.masai.model.Reservation;
import com.masai.service.ReservationService;

//import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

	@Autowired
	private ReservationService rService;
	
	
	
	@PostMapping("/add/{busId}")
	public ResponseEntity<Reservation> addNewReservationHandler(@PathVariable Integer busId,@Valid  @RequestBody Reservation reservation) throws ReservationException, BusException{
		
		Reservation saveReservation = rService.addNewReservation(busId ,reservation);
		
		return new ResponseEntity<Reservation>(saveReservation, HttpStatus.CREATED);
	}
	
	
	@PutMapping("/update")
	public ResponseEntity<Reservation> updateReservationHandler(@Valid   @RequestBody Reservation reservation) throws ReservationException{
		
		Reservation updateReservation  = rService.updateReservation(reservation);
		
		return new ResponseEntity<Reservation>(updateReservation, HttpStatus.ACCEPTED);
		
	}
	
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Reservation> deleteReservationHandler(@Valid   @PathVariable("id") Integer reservationId) throws ReservationException{
		
		Reservation deleteReservation = rService.deleteReservation(reservationId);
		
		return new ResponseEntity<Reservation>(deleteReservation, HttpStatus.OK);
		
		
	}
	
	
	
	
	@GetMapping("/view/{rid}")
	public ResponseEntity<Reservation> viewReservationByIdHandler(@Valid  @PathVariable("rid") Integer reservationId) throws ReservationException{
		
		Reservation viewReservation = rService.viewReservationById(reservationId);
		
		return new ResponseEntity<Reservation>(viewReservation, HttpStatus.OK);
		
		
	}
	
	
	@GetMapping("/viewall")
	public ResponseEntity<List<Reservation>> viewAllReservationHandler() throws ReservationException{
		
		List<Reservation> reservations = rService.viewAllReservation();
		
		
		return new ResponseEntity<List<Reservation>>(reservations, HttpStatus.OK);	
		
	}
	
	
	@PostMapping("/viewbydate")
	public ResponseEntity<List<Reservation>> getAllReservationByDate(@Valid  @RequestParam("localdate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) throws ReservationException{
		
		
		System.out.println(date);
		
		List<Reservation> reservation = rService.getAllReservationByDate(date);
		
		return new ResponseEntity<List<Reservation>>(reservation, HttpStatus.OK);
		
	}
	
	
	
	
	
	
}
