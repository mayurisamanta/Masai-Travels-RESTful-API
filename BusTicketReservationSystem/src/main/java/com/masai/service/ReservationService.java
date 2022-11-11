package com.masai.service;

import com.masai.exception.ReservationException;
import com.masai.model.Reservation;

public interface ReservationService {

	public Reservation addNewReservation(Reservation reservation)throws ReservationException;
	
	public Reservation updateReservation(Reservation reservation)throws ReservationException;
	
	public Reservation deleteReservation(Integer reservationId) throws ReservationException;
	
}
