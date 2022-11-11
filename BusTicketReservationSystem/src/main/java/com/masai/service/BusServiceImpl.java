package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.model.Bus;
import com.masai.repository.BusDao;

@Service
public class BusServiceImpl implements BusService{

	@Autowired
	private BusDao busdao;
	@Override
	public Bus addBus(Bus bus) {
		
		
		return busdao.save(bus);
		
	}

	@Override
	public Bus updateBus(Bus bus) {
		
	Optional<Bus> opt=	busdao.findById(bus.getBusId());
	if(opt.isPresent()) {
	Bus exbus=opt.get();
		return busdao.save(bus);
	}
	}

	@Override
	public Bus deleteBus(Integer busId) {
		Optional<Bus> opt=	busdao.findById(busId);
		if(opt.isPresent()) {
			Bus exbus=opt.get();
				busdao.delete(exbus);
				return exbus;
			}
	}

	@Override
	public Bus viewBus(Integer busId) {
		Optional<Bus> opt=	busdao.findById(busId);
		if(opt.isPresent()) {
			Bus exbus=opt.get();
				
				return exbus;
			}
	}

	@Override
	public List<Bus> viewBusByType(String busType) {
		
	}

	@Override
	public List<Bus> viewAllBus() {
		List<Bus> buslist=	busdao.findAll();
		return buslist;
		
	}

}
