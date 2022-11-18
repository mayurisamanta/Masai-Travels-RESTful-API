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
import com.masai.exception.UserException;
import com.masai.model.Reservation;
import com.masai.service.ReservationService;

//import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

	@Autowired
	private ReservationService rService;
	
	
	
	@PostMapping("/add/{busId}")
	public ResponseEntity<Reservation> addNewReservationHandler(@PathVariable Integer busId,@Valid  @RequestBody Reservation reservation,@RequestParam String key) throws ReservationException, BusException, UserException{
		
		Reservation saveReservation = rService.addNewReservation(busId ,reservation,key);
		
		return new ResponseEntity<Reservation>(saveReservation, HttpStatus.CREATED);
	}
	
	
	@PutMapping("/update")
	public ResponseEntity<Reservation> updateReservationHandler(@Valid   @RequestBody Reservation reservation,@RequestParam String key) throws ReservationException, UserException{
		
		Reservation updateReservation  = rService.updateReservation(reservation,key);
		
		return new ResponseEntity<Reservation>(updateReservation, HttpStatus.ACCEPTED);
		
	}
	
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Reservation> deleteReservationHandler(@Valid   @PathVariable("id") Integer reservationId,@RequestParam String key) throws ReservationException, UserException{
		
		Reservation deleteReservation = rService.deleteReservation(reservationId,key);
		
		return new ResponseEntity<Reservation>(deleteReservation, HttpStatus.OK);
		
		
	}
	
	
	
	
	@GetMapping("/view/{rid}")
	public ResponseEntity<Reservation> viewReservationByIdHandler(@Valid  @PathVariable("rid") Integer reservationId,@RequestParam String key) throws ReservationException, UserException{
		
		Reservation viewReservation = rService.viewReservationById(reservationId,key);
		
		return new ResponseEntity<Reservation>(viewReservation, HttpStatus.OK);
		
		
	}
	
	
	@GetMapping("/viewall")
	public ResponseEntity<List<Reservation>> viewAllReservationHandler(@RequestParam String key) throws ReservationException, UserException{
		
		List<Reservation> reservations = rService.viewAllReservation(key);
		
		
		return new ResponseEntity<List<Reservation>>(reservations, HttpStatus.OK);	
		
	}
	
	
	@PostMapping("/viewbydate")
	public ResponseEntity<List<Reservation>> getAllReservationByDateHandler(@Valid  @RequestParam("localdate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date, @RequestParam String key) throws ReservationException, UserException{
		
		
		System.out.println(date);
		
		List<Reservation> reservation = rService.getAllReservationByDate(date,key);
		
		return new ResponseEntity<List<Reservation>>(reservation, HttpStatus.OK);
		
	}
	
	
	
	
	
	
}
