//package com.masai.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.masai.exception.BusException;
//import com.masai.model.Bus;
//import com.masai.service.BusServiceImpl;
//
//
//
//@RestController
//public class BusController {
//
//	@Autowired
//	private BusServiceImpl busService;
//	
//	@PostMapping("/addBus")
//	public ResponseEntity<Bus> addBusHandler(@RequestBody Bus bus)throws BusException{
//		
//		Bus savedBus = busService.addBus(bus);
//		
//		return new ResponseEntity<Bus>(savedBus,HttpStatus.OK);
//		
//	}
//	
//	@PutMapping("/updateBus")
//	public ResponseEntity<Bus> updateBusHandler(@RequestBody Bus bus)throws BusException{
//		
//		Bus updatedBus = busService.updateBus(bus);
//		
//		return new ResponseEntity<Bus>(updatedBus,HttpStatus.OK);
//		
//	}
//	
//	@DeleteMapping("/deleteBus/{busId}/{routeId}")
//	public ResponseEntity<Bus> deleteBusHandler(@PathVariable("busId") Integer busId)throws BusException{
//		
//		Bus deletedBus = busService.deleteBus(busId);
//		
//		return new ResponseEntity<Bus>(deletedBus,HttpStatus.OK);
//	}
//	
//	@GetMapping("/viewBus/{busId}")
//	public ResponseEntity<Bus> viewBusHandler(@PathVariable("busId") Integer busId) throws BusException{
//		
//		Bus busById = busService.viewBus(busId);
//		
//		return new ResponseEntity<Bus>(busById,HttpStatus.OK);
//		
//	}
//	
//	@GetMapping("/viewBusByType/{busType}")
//	public ResponseEntity<List<Bus>> viewBusByTypeHandler(@PathVariable("busType") String busType) throws BusException{
//		
//		List<Bus> busByType = busService.viewBusByType(busType);
//		
//		return new ResponseEntity<List<Bus>>(busByType,HttpStatus.OK);
//		
//	}
//	
//	@GetMapping("/viewAllBus")
//	public ResponseEntity<List<Bus>> viewAllBus() throws BusException{
//		
//		List<Bus> viewAllBus = busService.viewAllBus();
//		
//		return new ResponseEntity<List<Bus>>(viewAllBus,HttpStatus.OK);
//		
//	}
//}
