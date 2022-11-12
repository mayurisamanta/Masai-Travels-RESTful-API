package com.masai.service;

import java.lang.StackWalker.Option;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.BusException;
import com.masai.exception.ReservationException;
import com.masai.model.Bus;
import com.masai.model.Reservation;
import com.masai.repository.BusDao;
import com.masai.repository.ReservationDao;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	private ReservationDao rDao;

	@Autowired
	private BusDao bdao;
	
	
	@Override
	public Reservation addNewReservation(Integer busId, Reservation reservation) throws ReservationException, BusException {
		
		Bus b = bdao.findById(busId).orElseThrow(() -> new BusException("Bus with Id " + busId + " not found"));
		
		reservation.setBus(b);
		
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


	@Override
	public Reservation viewReservationById(Integer reservationId) throws ReservationException {
		
		Optional<Reservation> opt = rDao.findById(reservationId);
		
		if(opt.isPresent()) {
			
			
			Reservation reservation = opt.get();
			
			return reservation;
			
		}
		else {
			
			throw new ReservationException("Reservation is not present with this Reservation ID :" + reservationId);
			
		}
	}


	@Override
	public List<Reservation> viewAllReservation() throws ReservationException {
		
		
		List<Reservation> reservation = rDao.findAll();
		
		
		if(reservation.size() != 0) {
			
			return reservation;
		}
		else {
			throw new ReservationException("No Reservation is available...!");
		}
		
		
	}


	@Override
	public List<Reservation> getAllReservationByDate(LocalDate date) throws ReservationException {
		
		
		
		
		
		List<Reservation> reservation = rDao.findAll();
		
		List<Reservation> reservationByDate = new ArrayList<>();
		
		if(reservation.size() != 0) {
			
			
			for(Reservation s : reservation) {
				
				
				if(date.isEqual(s.getReservationDate())) {
	
					reservationByDate.add(s);
				}
		
			}
			
			
			if(reservationByDate.size() != 0) {
				
				return reservationByDate;
			}
			else {
				throw new ReservationException("No Reservation is available on this Date...");
			}
			
		}
		else {
			throw new ReservationException("No Reservation is available...!");
		}
		

	}


	
	
}
