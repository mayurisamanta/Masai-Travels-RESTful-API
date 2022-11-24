package com.masai.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Route {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer routeId;
	
	private String routeFrom;
	
	private String routeTo;
	
	private Integer distance;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "route")
	private List<Bus> buslist;



//	public Route(Integer routeId, String routeFrom, String routeTo, Integer distance, List<Bus> buslist) {
//		super();
//		this.routeId = routeId;
//		this.routeFrom = routeFrom;
//		this.routeTo = routeTo;
//		this.distance = distance;
//		this.buslist = buslist;
//	}

	public Route(Integer routeId, String routeFrom, String routeTo, Integer distance, List<Bus> buslist) {
		super();
		this.routeId = routeId;
		this.routeFrom = routeFrom;
		this.routeTo = routeTo;
		this.distance = distance;
		this.buslist = buslist;
	}



	public Route() {
		super();
	}


	public Integer getRouteId() {
		return routeId;
	}


	public void setRouteId(Integer routeId) {
		this.routeId = routeId;
	}


	public String getRouteFrom() {
		return routeFrom;
	}


	public void setRouteFrom(String routeFrom) {
		this.routeFrom = routeFrom;
	}


	public String getRouteTo() {
		return routeTo;
	}


	public void setRouteTo(String routeTo) {
		this.routeTo = routeTo;
	}


	public Integer getDistance() {
		return distance;
	}


	public void setDistance(Integer distance) {
		this.distance = distance;
	}


	public List<Bus> getBuslist() {
		return buslist;
	}


	public void setBuslist(List<Bus> buslist) {
		this.buslist = buslist;
	}


	@Override
	public String toString() {
		return "Route [routeId=" + routeId + ", routeFrom=" + routeFrom + ", routeTo=" + routeTo + ", distance="
				+ distance + "]";
	}
	
}
