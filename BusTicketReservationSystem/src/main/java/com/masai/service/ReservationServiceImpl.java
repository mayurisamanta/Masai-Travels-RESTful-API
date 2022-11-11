package com.masai.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

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
		// TODO Auto-generated method stub
		return null;
	}


	
	
}
