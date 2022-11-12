package com.masai.service;

import java.time.LocalDate;
import java.util.List;

import com.masai.exception.BusException;
import com.masai.exception.ReservationException;
import com.masai.exception.UserException;
import com.masai.model.Reservation;

public interface ReservationService {

	public Reservation addNewReservation(Integer busId,Reservation reservation, String key)throws ReservationException, BusException, UserException;
	
	public Reservation updateReservation(Reservation reservation, String key)throws ReservationException, UserException;
	
	public Reservation deleteReservation(Integer reservationId, String key) throws ReservationException, UserException;
	
	public Reservation viewReservationById(Integer reservationId, String key)throws ReservationException, UserException;
	
	public List<Reservation> viewAllReservation( String key)throws ReservationException, UserException;
	
	
	public List<Reservation> getAllReservationByDate(LocalDate date,String key)throws ReservationException, UserException;
	
}
