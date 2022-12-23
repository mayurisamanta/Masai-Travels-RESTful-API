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



@RestController
@RequestMapping("Bus")
public class BusController {

	@Autowired
	private BusServiceImpl busService;
	
//	adding bus by post method
	
	@PostMapping("/add")
	public ResponseEntity<Bus> addBusHandler(@RequestBody Bus bus,@RequestParam String key)throws BusException, UserException{
		
		Bus savedBus = busService.addBus(bus,key);
		
		return new ResponseEntity<Bus>(savedBus,HttpStatus.CREATED);
		
	}
	
	@PutMapping("/update")
	public ResponseEntity<Bus> updateBusHandler(@RequestBody Bus bus,@RequestParam String key)throws BusException, UserException{
		
		Bus updatedBus = busService.updateBus(bus,key);
		
		return new ResponseEntity<Bus>(updatedBus,HttpStatus.ACCEPTED);
		
	}
	
	@DeleteMapping("/delete/{busId}")
	public ResponseEntity<Bus> deleteBusHandler(@PathVariable("busId") Integer busId,@RequestParam String key)throws BusException, UserException{
		
		Bus deletedBus = busService.deleteBus(busId,key);
		
		return new ResponseEntity<Bus>(deletedBus,HttpStatus.OK);
	}
	
	@GetMapping("/view/{busId}")
	public ResponseEntity<Bus> viewBusHandler(@PathVariable("busId") Integer busId,@RequestParam String key) throws BusException, UserException{
		
		Bus busById = busService.viewBus(busId,key);
		
		return new ResponseEntity<Bus>(busById,HttpStatus.FOUND);
		
	}
	
	@GetMapping("/viewBusByType/{busType}")
	public ResponseEntity<List<Bus>> viewBusByTypeHandler(@PathVariable("busType") String busType,@RequestParam String key) throws BusException, UserException{
		
		List<Bus> busByType = busService.viewBusByType(busType,key);
		
		return new ResponseEntity<List<Bus>>(busByType,HttpStatus.FOUND);
		
	}
	
	@GetMapping("/viewAllBus")
	public ResponseEntity<List<Bus>> viewAllBusHandler(@RequestParam String key) throws BusException, UserException{
		
		List<Bus> viewAllBus = busService.viewAllBus(key);
		
		return new ResponseEntity<List<Bus>>(viewAllBus,HttpStatus.OK);
		
	}
}
