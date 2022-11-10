package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.RouteException;
import com.masai.model.Route;
import com.masai.repository.RouteRepo;

@Service
public class RouteServiceImpl implements RouteService{
	
	@Autowired
	private RouteRepo rRepo;

	@Override
	public Route addRoute(Route route) throws RouteException {
		
		return  rRepo.save(route);
	}

	@Override
	public Route updateRoute(Route route) throws RouteException {
		
	   Optional<Route> opt = rRepo.findById(route.getRouteId());
	   
	   if(opt.isPresent()) {
		   
		  return rRepo.save(route);
		   
	   }
	   else {
		   throw new RouteException("No route exist to update please save the route first..");
	   }
	
		
	}

	@Override
	public Route deleteRoute(Integer routeId) throws RouteException {
		
	Optional<Route> opt =	rRepo.findById(routeId);
	
	if(opt.isPresent()) {
		
		Route rot = opt.get();
		
		rRepo.delete(rot);
		
		return rot;
		
	}
	else {
		throw new RouteException("No route found on this "+routeId+" id");
	}
	
	}

	@Override
	public Route viewRoute(Integer routeId) throws RouteException {
		
	Optional<Route> opt =rRepo.findById(routeId);
	
     if(opt.isPresent()) {
    	 
    	 return opt.get();
     }
     else {
    	 throw new RouteException("No route found on this "+routeId+" id");
     }
		
	}

	@Override
	public List<Route> viewAllRoute() throws RouteException {
		
		List<Route> routeList = rRepo.findAll();
		
		if(routeList.size()!=0) {
			
			return routeList;
		}else {
			throw new RouteException("Route list is empty");
		}
		
		
	}

}
