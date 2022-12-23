package com.masai.service;

import java.util.List;

import com.masai.exception.BusException;
import com.masai.exception.UserException;
import com.masai.model.Bus;

public interface BusService {
	
//methods you can find inside bus module
	public Bus addBus(Bus bus, String key) throws BusException, UserException;
	
	public Bus updateBus(Bus bus, String key)throws BusException, UserException;
	
	public Bus deleteBus(Integer busId, String key)throws BusException, UserException;
	
	public Bus viewBus(Integer busId, String key)throws BusException, UserException;
	
	public List<Bus> viewBusByType(String busType, String key)throws BusException, UserException;
	
	public List<Bus> viewAllBus(String key)throws BusException, UserException;

}
