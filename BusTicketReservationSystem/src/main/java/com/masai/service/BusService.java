package com.masai.service;

import java.util.List;

import com.masai.model.Bus;

public interface BusService {
	
	public Bus addBus(Bus bus) ;
	public Bus updateBus(Bus bus);
	public Bus deleteBus(Integer busId);
	public Bus viewBus(Integer busId);
	public List<Bus> viewBusByType(String busType);
	public List<Bus> viewAllBus();

}
