package com.masai.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Reservation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer reservationId;
	
	private String reservationStatus;
	
	private String reservationType;
	
	private LocalDate reservationDate;
	
	private LocalTime reservationTime;
	
	private String source;
	
	private String destination;
	
	
	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
	private Bus bus;


	public Reservation(Integer reservationId, String reservationStatus, String reservationType,
			LocalDate reservationDate, LocalTime reservationTime, String source, String destination, Bus bus) {
		super();
		this.reservationId = reservationId;
		this.reservationStatus = reservationStatus;
		this.reservationType = reservationType;
		this.reservationDate = reservationDate;
		this.reservationTime = reservationTime;
		this.source = source;
		this.destination = destination;
		this.bus = bus;
	}


	public Reservation() {
		super();
	}


	public Integer getReservationId() {
		return reservationId;
	}


	public void setReservationId(Integer reservationId) {
		this.reservationId = reservationId;
	}


	public String getReservationStatus() {
		return reservationStatus;
	}


	public void setReservationStatus(String reservationStatus) {
		this.reservationStatus = reservationStatus;
	}


	public String getReservationType() {
		return reservationType;
	}


	public void setReservationType(String reservationType) {
		this.reservationType = reservationType;
	}


	public LocalDate getReservationDate() {
		return reservationDate;
	}


	public void setReservationDate(LocalDate reservationDate) {
		this.reservationDate = reservationDate;
	}


	public LocalTime getReservationTime() {
		return reservationTime;
	}


	public void setReservationTime(LocalTime reservationTime) {
		this.reservationTime = reservationTime;
	}


	public String getSource() {
		return source;
	}


	public void setSource(String source) {
		this.source = source;
	}


	public String getDestination() {
		return destination;
	}


	public void setDestination(String destination) {
		this.destination = destination;
	}


	public Bus getBus() {
		return bus;
	}


	public void setBus(Bus bus) {
		this.bus = bus;
	}


	@Override
	public String toString() {
		return "Reservation [reservationId=" + reservationId + ", reservationStatus=" + reservationStatus
				+ ", reservationType=" + reservationType + ", reservationDate=" + reservationDate + ", reservationTime="
				+ reservationTime + ", source=" + source + ", destination=" + destination + "]";
	}

}
