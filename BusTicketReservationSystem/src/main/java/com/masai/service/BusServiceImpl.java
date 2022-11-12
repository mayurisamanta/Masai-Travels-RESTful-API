package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.BusException;
import com.masai.model.Bus;
import com.masai.repository.BusDao;

@Service
public class BusServiceImpl implements BusService{

	@Autowired
	private BusDao busdao;
	@Override

	public Bus addBus(Bus bus)throws BusException{
		
		return busdao.save(bus);
		
	}

	@Override

	public Bus updateBus(Bus bus)throws BusException{
		
	Optional<Bus> opt=	busdao.findById(bus.getBusId());
	if(opt.isPresent()) {
	Bus exbus = opt.get();
		return busdao.save(bus);
	}

		
		throw new BusException("bus doesn't exists with the above details");

	}

	@Override
	public Bus deleteBus(Integer busId)throws BusException {
		Optional<Bus> opt=	busdao.findById(busId);
		if(opt.isPresent()) {
			Bus exbus=opt.get();
				busdao.delete(exbus);
				return exbus;
			}

		throw new BusException("bus doesn't exists with this "+busId+" id");
	}

	@Override

	public Bus viewBus(Integer busId) throws BusException{

		Optional<Bus> opt=	busdao.findById(busId);
		if(opt.isPresent()) {
			Bus exbus=opt.get();
				
				return exbus;
			}
		throw new BusException("bus doesn't exists with this "+busId+" id");
	}

	@Override

	

        return bList;

		return bList;

	}

//	public List<Bus> viewBusByType(String busType) throws BusException {
//   List<Bus> bList = busdao.findByBusType(busType);
//		
//		if(bList.size()==0) {
//			throw new BusException("bus list is empty");
//		}
//
//		return bList;
//	}

	@Override

	public List<Bus> viewAllBus() throws BusException{

		List<Bus> buslist=	busdao.findAll();
		if(buslist.size()==0) {
			throw new BusException("bus list is empty");
		}
		return buslist;
		
	}

}
