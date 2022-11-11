package com.masai.service;

import java.util.List;

import com.masai.exception.RouteException;
import com.masai.model.Route;

public interface RouteService {

	public Route addRoute(Route route) throws RouteException;
	
	public Route updateRoute(Route route) throws RouteException;
	
	public Route deleteRoute(Integer routeId) throws RouteException;
	
	public Route viewRoute(Integer routeId) throws RouteException;
	
	public List<Route> viewAllRoute() throws RouteException;
	
	
	
	
}
