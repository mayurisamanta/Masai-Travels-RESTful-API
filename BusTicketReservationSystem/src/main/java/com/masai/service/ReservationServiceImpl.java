package com.masai.service;

import java.lang.StackWalker.Option;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.ReservationException;
import com.masai.model.Bus;
import com.masai.model.Reservation;
import com.masai.repository.ReservationDao;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	private ReservationDao rDao;

	
	
	@Override
	public Reservation addNewReservation(Reservation reservation) throws ReservationException {
		
		
		return rDao.save(reservation);
		
	}


	@Override
	public Reservation updateReservation(Reservation reservation) throws ReservationException {
		
		Optional<Reservation> opt = rDao.findById(reservation.getReservationId());
		
		if(opt.isPresent()) {
			
			
			Reservation updateReservation = rDao.save(reservation);
			
			return updateReservation;
			
			
		}
		else {
			
			throw new ReservationException("Invalid Student Details....!");
			
		}
		
	}


	@Override
	public Reservation deleteReservation(Integer reservationId) throws ReservationException {
		
		Optional<Reservation> opt = rDao.findById(reservationId);
		
		if(opt.isPresent()) {
			
			
			Reservation existingReservation = opt.get();
			
			rDao.delete(existingReservation);
			
			return existingReservation;
			
		}
		else {
			
			throw new ReservationException("Reservation is not present with this Reservation ID :" + reservationId);
			
		}
		
	}


	
	
}
