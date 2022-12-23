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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exception.BusException;
import com.masai.exception.UserException;
import com.masai.model.Bus;
import com.masai.service.BusServiceImpl;


//this class is generating APIs for different  method.
@RestController
@RequestMapping("Bus")
public class BusController {

//	@Autowired annotation can be used to autowire bean on the setter method 
	
	@Autowired
	private BusServiceImpl busService;
	
//	adding bus by post method by providing bus details and authorization key
	
	@PostMapping("/add")
	public ResponseEntity<Bus> addBusHandler(@RequestBody Bus bus,@RequestParam String key)throws BusException, UserException{
		
		Bus savedBus = busService.addBus(bus,key);
		
		return new ResponseEntity<Bus>(savedBus,HttpStatus.CREATED);
		
	}
	
//	update bus details by providing bus details and authorization key
	
	@PutMapping("/update")
	public ResponseEntity<Bus> updateBusHandler(@RequestBody Bus bus,@RequestParam String key)throws BusException, UserException{
		
		Bus updatedBus = busService.updateBus(bus,key);
		
		return new ResponseEntity<Bus>(updatedBus,HttpStatus.ACCEPTED);
		
	}
	
	//	delete bus details by providing bus id and authorization key
	
	@DeleteMapping("/delete/{busId}")
	public ResponseEntity<Bus> deleteBusHandler(@PathVariable("busId") Integer busId,@RequestParam String key)throws BusException, UserException{
		
		Bus deletedBus = busService.deleteBus(busId,key);
		
		return new ResponseEntity<Bus>(deletedBus,HttpStatus.OK);
	}
	
//	Get bus details by providing bus Id and authorization key
	
	@GetMapping("/view/{busId}")
	public ResponseEntity<Bus> viewBusHandler(@PathVariable("busId") Integer busId,@RequestParam String key) throws BusException, UserException{
		
		Bus busById = busService.viewBus(busId,key);
		
		return new ResponseEntity<Bus>(busById,HttpStatus.FOUND);
		
	}
	
//	Get bus details by providing bus type and authorization key
	
	@GetMapping("/viewBusByType/{busType}")
	public ResponseEntity<List<Bus>> viewBusByTypeHandler(@PathVariable("busType") String busType,@RequestParam String key) throws BusException, UserException{
		
		List<Bus> busByType = busService.viewBusByType(busType,key);
		
		return new ResponseEntity<List<Bus>>(busByType,HttpStatus.FOUND);
		
	}
	
//	Getting  all bus details by providing authorization key
	
	@GetMapping("/viewAllBus")
	public ResponseEntity<List<Bus>> viewAllBusHandler(@RequestParam String key) throws BusException, UserException{
		
		List<Bus> viewAllBus = busService.viewAllBus(key);
		
		return new ResponseEntity<List<Bus>>(viewAllBus,HttpStatus.OK);
		
	}
}
