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
import com.masai.exception.UserException;
import com.masai.model.Bus;
import com.masai.model.CurrentUserSession;
import com.masai.model.Reservation;
import com.masai.model.User;
import com.masai.repository.BusDao;
import com.masai.repository.ReservationDao;
import com.masai.repository.SessionRepo;
import com.masai.repository.UserRepo;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	private ReservationDao rDao;

	@Autowired
	private BusDao bdao;
	
	@Autowired
	private SessionRepo srepo;
	
	@Autowired
	private UserRepo uRepo;
	
	@Override
	public Reservation addNewReservation(Integer busId, Reservation reservation, String key) throws ReservationException, BusException,UserException{
		
		CurrentUserSession loggedInUser=srepo.findByUuid(key);
		if(loggedInUser==null) {
			throw new UserException("Please provide a valid key to add reservation");
		}
		
		User u = uRepo.findById(loggedInUser.getUserId()).orElseThrow(()-> new UserException("User with User Id "+ loggedInUser.getUserId()+" does not exist"));
		
		
		Bus b = bdao.findById(busId).orElseThrow(() -> new BusException("Bus with Id " + busId + " not found"));
		
		if (!reservation.getSource().equalsIgnoreCase(b.getRouteForm()) || !reservation.getDestination().equalsIgnoreCase(b.getRouteTo()))
			throw new ReservationException("Bus is not available for this route");
		
		if (b.getAvailabeSeats() <= 0) throw new ReservationException("Seats are not available");
		
		
		b.setAvailabeSeats(b.getAvailabeSeats() - 1);
		
		reservation.setReservationType("Online");
		reservation.setReservationStatus("Booked");
		reservation.setReservationTime(LocalTime.now().toString());
		reservation.setBus(b);
		
		u.setReservation(reservation);
		
		return rDao.save(reservation);
		
	}


	@Override
	public Reservation updateReservation(Reservation reservation, String key) throws ReservationException, UserException {
		
		CurrentUserSession loggedInUser=srepo.findByUuid(key);
		if(loggedInUser==null) {
			throw new UserException("Please provide a valid key to add reservation");
		}
		
		Optional<Reservation> opt = rDao.findById(reservation.getReservationId());
		
		if(opt.isPresent()) {
			User u = uRepo.findById(loggedInUser.getUserId()).orElseThrow(()-> new UserException("User with User Id "+ loggedInUser.getUserId()+" does not exist"));
			
			Reservation curr = u.getReservation();
			
			if (reservation.getDestination() != null) curr.setDestination(reservation.getDestination());
			if (reservation.getReservationDate() != null) curr.setReservationDate(reservation.getReservationDate());
			if (reservation.getReservationStatus() != null) curr.setReservationStatus(reservation.getReservationStatus());
			if (reservation.getReservationTime() != null) curr.setReservationTime(reservation.getReservationTime().toString());
			if (reservation.getReservationType() != null) curr.setReservationType(reservation.getReservationType());
			if (reservation.getSource() != null) curr.setSource(reservation.getSource());
			
			Reservation updateReservation = rDao.save(curr);
			
			u.setReservation(updateReservation);
			
			return updateReservation;
			
			
		}
		else {
			
			throw new ReservationException("Reservation not found");
			
		}
		
	}


	@Override
	public Reservation deleteReservation(Integer reservationId, String key) throws ReservationException, UserException {
		
		CurrentUserSession loggedInUser=srepo.findByUuid(key);
		if(loggedInUser==null) {
			throw new UserException("Please provide a valid key to add reservation");
		}
		
		
		Optional<Reservation> opt = rDao.findById(reservationId);
		
		if(opt.isPresent()) {
			
			
			Reservation existingReservation = opt.get();
			Optional<User> u = uRepo.findById(loggedInUser.getUserId());
			
			User currUser = u.get();
			
			Bus b = bdao.findById(currUser.getReservation().getBus().getBusId()).orElseThrow(() -> new ReservationException("Bus with Id " + currUser.getReservation().getBus().getBusId() + " not found"));
			b.setAvailabeSeats(b.getAvailabeSeats()+1);
			
			currUser.setReservation( null);
			
			rDao.delete(existingReservation);
			
			return existingReservation;
			
		}
		else {
			
			throw new ReservationException("Reservation is not present with this Reservation ID :" + reservationId);
			
		}
		
	}


	@Override
	public Reservation viewReservationById(Integer reservationId, String key) throws ReservationException, UserException {
		
		
		CurrentUserSession loggedInUser=srepo.findByUuid(key);
		if(loggedInUser==null) {
			throw new UserException("Please provide a valid key to add reservation");
		}
		
		
		Optional<Reservation> opt = rDao.findById(reservationId);
		
		if(opt.isPresent()) {
			
			
			if (loggedInUser.getType().equalsIgnoreCase("Admin")) {
				
				Reservation reservation = opt.get();
				
				return reservation;
			}
			else {
				User u=uRepo.findById(loggedInUser.getUserId())
						.orElseThrow(()-> new UserException("User with User Id "+loggedInUser.getUserId()+" does not exist"));
				if(u.getUserLoginId()==loggedInUser.getUserId()) {
					Reservation reservation = opt.get();
					
					return reservation;
				}
				else {
					throw new UserException("Invalid User details, please login first");
				}
			}
			
			
			
			
		}
		else {
			
			throw new ReservationException("Reservation is not present with this Reservation ID :" + reservationId);
			
		}
	}


	@Override
	public List<Reservation> viewAllReservation(String key) throws ReservationException, UserException {
		
		CurrentUserSession loggedInUser=srepo.findByUuid(key);
		if(loggedInUser==null) {
			throw new UserException("Please provide a valid key to add reservation");
		}
		if (loggedInUser.getType().equalsIgnoreCase("Admin")) {
			
			List<Reservation> reservation = rDao.findAll();
			
			
			if(reservation.size() != 0) {
				
				return reservation;
			}
			else {
				throw new ReservationException("Reservation not found");
			}
		}
		else throw new ReservationException("Access Denied");
		
		
		
		
	}


	@Override
	public List<Reservation> getAllReservationByDate(LocalDate date, String key) throws ReservationException, UserException {
		
		CurrentUserSession loggedInUser=srepo.findByUuid(key);
		if(loggedInUser==null) {
			throw new UserException("Please provide a valid key to add reservation");
		}
		if (loggedInUser.getType().equalsIgnoreCase("Admin")) {
			
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
		else throw new ReservationException("Access Denied");
		
		
		
		
		

	}


	
	
}
