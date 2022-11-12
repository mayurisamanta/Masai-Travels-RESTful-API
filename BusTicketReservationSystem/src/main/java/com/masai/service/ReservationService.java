package com.masai.service;

import java.time.LocalDate;
import java.util.List;

import com.masai.exception.ReservationException;
import com.masai.model.Reservation;

public interface ReservationService {

	public Reservation addNewReservation(Reservation reservation)throws ReservationException;
	
	public Reservation updateReservation(Reservation reservation)throws ReservationException;
	
	public Reservation deleteReservation(Integer reservationId) throws ReservationException;
	
	public Reservation viewReservationById(Integer reservationId)throws ReservationException;
	
	public List<Reservation> viewAllReservation()throws ReservationException;
	
	
	public List<Reservation> getAllReservationByDate(LocalDate date)throws ReservationException;
	
}
