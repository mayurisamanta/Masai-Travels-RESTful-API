package com.masai.model;

import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Bus {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer busId;
	
	private String busName;
	
	private String driverName;
	
	private String busType;
	
	private String routeForm;
	
	private String routeTo;
	
	private LocalTime arrivalTime;
	
	private LocalTime departureTime;
	
	private Integer seats;
	
	private Integer availabeSeats;

	public Bus(Integer busId, String busName, String driverName, String busType, String routeForm, String routeTo,
			LocalTime arrivalTime, LocalTime departureTime, Integer seats, Integer availabeSeats) {
		super();
		this.busId = busId;
		this.busName = busName;
		this.driverName = driverName;
		this.busType = busType;
		this.routeForm = routeForm;
		this.routeTo = routeTo;
		this.arrivalTime = arrivalTime;
		this.departureTime = departureTime;
		this.seats = seats;
		this.availabeSeats = availabeSeats;
	}

	public Bus() {
		super();
	}

	public Integer getBusId() {
		return busId;
	}

	public void setBusId(Integer busId) {
		this.busId = busId;
	}

	public String getBusName() {
		return busName;
	}

	public void setBusName(String busName) {
		this.busName = busName;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getBusType() {
		return busType;
	}

	public void setBusType(String busType) {
		this.busType = busType;
	}

	public String getRouteForm() {
		return routeForm;
	}

	public void setRouteForm(String routeForm) {
		this.routeForm = routeForm;
	}

	public String getRouteTo() {
		return routeTo;
	}

	public void setRouteTo(String routeTo) {
		this.routeTo = routeTo;
	}

	public LocalTime getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(LocalTime arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public LocalTime getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(LocalTime departureTime) {
		this.departureTime = departureTime;
	}

	public Integer getSeats() {
		return seats;
	}

	public void setSeats(Integer seats) {
		this.seats = seats;
	}

	public Integer getAvailabeSeats() {
		return availabeSeats;
	}

	public void setAvailabeSeats(Integer availabeSeats) {
		this.availabeSeats = availabeSeats;
	}

	@Override
	public String toString() {
		return "Bus [busId=" + busId + ", busName=" + busName + ", driverName=" + driverName + ", busType=" + busType
				+ ", routeForm=" + routeForm + ", routeTo=" + routeTo + ", arrivalTime=" + arrivalTime
				+ ", departureTime=" + departureTime + ", seats=" + seats + ", availabeSeats=" + availabeSeats + "]";
	}
	
}
