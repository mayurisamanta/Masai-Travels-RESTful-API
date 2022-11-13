package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.RouteException;
import com.masai.exception.UserException;
import com.masai.model.CurrentUserSession;
import com.masai.model.Route;
import com.masai.model.User;
import com.masai.repository.RouteRepo;
import com.masai.repository.SessionRepo;
import com.masai.repository.UserRepo;

@Service
public class RouteServiceImpl implements RouteService{
	
	@Autowired
	private RouteRepo rRepo;
	
	@Autowired
	private SessionRepo srepo;
	
	@Autowired
	private UserRepo uRepo;
	
	

	@Override
	public Route addRoute(Route route,String key) throws RouteException, UserException {
		CurrentUserSession loggedInUser=srepo.findByUuid(key);
		if(loggedInUser==null) {
			throw new UserException("Please provide a valid key to view user.");
		}
		if (loggedInUser.getType().equalsIgnoreCase("Admin")) {
			
		
			return  rRepo.save(route);
		}
		else throw new UserException("Access denied");
	}

	@Override
	public Route updateRoute(Route route,String key) throws RouteException, UserException {
		
		CurrentUserSession loggedInUser=srepo.findByUuid(key);
		
		if(loggedInUser==null) {
			throw new UserException("Please provide a valid key to update user");
		}
		
		if (loggedInUser.getType().equalsIgnoreCase("Admin")) {
			
			
			Optional<User> user = uRepo.findById(loggedInUser.getUserId());
			
			if(user.isPresent()) {
				
				Optional<Route> opt = rRepo.findById(route.getRouteId());
				
				
					   
					 Route existingRoute = opt.get();
					 
					 if (route.getDistance() != null) existingRoute.setDistance(route.getDistance());
					 if (route.getRouteFrom() != null) existingRoute.setRouteFrom(route.getRouteFrom());
					 if (route.getRouteTo() != null) existingRoute.setRouteTo(route.getRouteTo());
					 if (route.getBuslist() != null) existingRoute.setBuslist(route.getBuslist());
					 
					Route saved =  rRepo.save(existingRoute);
					
					return saved;
					   
				  }
				  else {
					 throw new RouteException("No route exist to update please save the route first..");
				  }
				
		
			
	
		   
		}
		else throw new UserException("Access denied");
		
		
		
	
		
	}

	@Override
	public Route deleteRoute(Integer routeId,String key) throws RouteException, UserException {
		
		CurrentUserSession loggedInUser=srepo.findByUuid(key);
		
		if(loggedInUser==null) {
			throw new UserException("Please provide a valid key to delete route");
		}
		
		if (loggedInUser.getType().equalsIgnoreCase("Admin")) {
			
			Optional<User> user = uRepo.findById(loggedInUser.getUserId());
			
			
			if(user.isPresent()) {
				
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
		}
		
		
		throw new UserException("Access denied");
		
	
	}

	@Override
	public Route viewRoute(Integer routeId,String key) throws RouteException, UserException {
		
		CurrentUserSession loggedInUser=srepo.findByUuid(key);
		
		if(loggedInUser==null) {
			throw new UserException("Please provide a valid key to view route");
		}	
		
		Optional<User> user = uRepo.findById(loggedInUser.getUserId());
		
		if(user.isPresent()) {
			
			Optional<Route> opt =rRepo.findById(routeId);
			
		     if(opt.isPresent()) {
		    	 
		    	 return opt.get();
		     }
		     else {
		    	 throw new RouteException("No route found on this "+routeId+" id");
		     }
		}
		
		throw new UserException("Access declained... ");
			
	}

	@Override
	public List<Route> viewAllRoute(String key) throws RouteException, UserException {
		
		CurrentUserSession loggedInUser=srepo.findByUuid(key);
		
		if(loggedInUser==null) {
			throw new UserException("Please provide a valid key to view route");
		}	
		
		Optional<User> user = uRepo.findById(loggedInUser.getUserId());
		
		if(user.isPresent()) {
			
			List<Route> routeList = rRepo.findAll();
			
			if(routeList.size()!=0) {
				
				return routeList;
			}else {
				throw new RouteException("Route list is empty");
			}
		}
		
		
		throw new UserException("Access declained... ");
		
	}

}
