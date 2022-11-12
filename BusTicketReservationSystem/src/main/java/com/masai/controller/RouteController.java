package com.masai.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exception.RouteException;
import com.masai.model.Route;
import com.masai.service.RouteServiceImpl;

@RestController
public class RouteController {
	
	@Autowired
	private RouteServiceImpl rService;
	
	@PostMapping("/addRoute")
    public ResponseEntity<Route> addRouteHandler(@RequestBody Route route) throws RouteException {
		
		Route rot = rService.addRoute(route);
		
		return new ResponseEntity<Route>(rot, HttpStatus.OK);
	}
	
	@PutMapping("/updateRoute")
	public ResponseEntity<Route> updateRouteHandler(@RequestBody Route route) throws RouteException{
		
	 Route rot = rService.updateRoute(route);
	 
	 return new ResponseEntity<Route>(rot, HttpStatus.OK);
		
	}
	
	@DeleteMapping("/deleteRoute/{routeId}")
	public ResponseEntity<Route> deleteRouteHandler(@PathVariable("routeId") Integer routeId) throws RouteException{
		
	Route rot =	rService.deleteRoute(routeId);
	
	return new ResponseEntity<Route>(rot,HttpStatus.OK);
		
	}
	
	@GetMapping("/getRoute/{routeId}")
	public ResponseEntity<Route> viewRouteHandler(@PathVariable("routeId") Integer routeId) throws RouteException{
		
		Route rot = rService.viewRoute(routeId);
		
		return new ResponseEntity<Route>(rot,HttpStatus.OK);
	}
	
	@GetMapping("/getAllRoute")
	public ResponseEntity<List<Route>> viewAllRouteHandler() throws RouteException{
		
     List<Route> rot = rService.viewAllRoute();
		
		return new ResponseEntity<List<Route>>(rot,HttpStatus.OK);
		
	}
	
}

