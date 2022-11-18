package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.BusException;
import com.masai.exception.UserException;
import com.masai.model.Bus;
import com.masai.model.CurrentUserSession;
import com.masai.model.Route;
import com.masai.repository.BusDao;
import com.masai.repository.RouteRepo;
import com.masai.repository.SessionRepo;

@Service
public class BusServiceImpl implements BusService{

	@Autowired
	private BusDao busdao;
	
	@Autowired
	private SessionRepo srepo;
	
	@Autowired
	RouteRepo rrepo;
	
	@Override
	public Bus addBus(Bus bus, String key)throws BusException, UserException{
		
		CurrentUserSession loggedInUser=srepo.findByUuid(key);
		
		if(loggedInUser==null) {
			throw new UserException("Please provide a valid key to delete user.");
		}
		
		if (loggedInUser.getType().equalsIgnoreCase("Admin")) {
			
			Route route =  rrepo.findByRouteFromAndRouteTo(bus.getRouteForm(), bus.getRouteTo());
			
			if(route!=null) {
				
				if(route.getBuslist().contains(bus)) {
					throw new BusException("Bus already exists");
				}
				
				bus.setRoute(route);
				
			}
			
			
			return busdao.save(bus);
		}
		else throw new UserException("Unauthorized Access! Only Admin can make changes");
		
	}

	@Override
	public Bus updateBus(Bus bus, String key)throws BusException, UserException{
		
		CurrentUserSession loggedInUser=srepo.findByUuid(key);
		if(loggedInUser==null) {
			throw new UserException("Please provide a valid key to delete user.");
		}
		if (loggedInUser.getType().equalsIgnoreCase("Admin")) {
			Optional<Bus> opt=	busdao.findById(bus.getBusId());
			if(opt.isPresent()) {
			Bus curr = opt.get();
			
				if (bus.getArrivalTime() != null) curr.setArrivalTime(bus.getArrivalTime().toString());
				if (bus.getAvailabeSeats() != null) curr.setAvailabeSeats(bus.getAvailabeSeats());
				if (bus.getBusName() != null) curr.setBusName(bus.getBusName());
				if (bus.getBusType() != null) curr.setBusType(bus.getBusType());
				if (bus.getDepartureTime() != null) curr.setDepartureTime(bus.getDepartureTime().toString());
				if (bus.getDriverName() != null) curr.setDriverName(bus.getDriverName());
				if (bus.getRouteForm() != null) curr.setRouteForm(bus.getRouteForm());
				if (bus.getRouteTo() != null) curr.setRouteTo(bus.getRouteTo());
				if (bus.getSeats() != null) curr.setSeats(bus.getSeats());
				
				Bus updated = busdao.save(curr);
				
				return updated;
			
			}
				throw new BusException("Bus with id " + bus.getBusId() + "does not exist");
			
		}
		else throw new UserException("Unauthorized Access! Only Admin can make changes");

	}

	@Override
	public Bus deleteBus(Integer busId, String key)throws BusException, UserException {
		
		CurrentUserSession loggedInUser=srepo.findByUuid(key);
		if(loggedInUser==null) {
			throw new UserException("Please provide a valid key to delete bus");
		}
		if (loggedInUser.getType().equalsIgnoreCase("Admin")) {
			Optional<Bus> opt=	busdao.findById(busId);
			if(opt.isPresent()) {
				Bus exbus=opt.get();
					busdao.delete(exbus);
					return exbus;
				}

			throw new BusException("bus doesn't exists with this "+busId+" id");
		}
		else throw new UserException("Unauthorized Access! Only Admin can make changes");
		
		
	}

	@Override
	public Bus viewBus(Integer busId, String key) throws BusException, UserException{

		CurrentUserSession loggedInUser=srepo.findByUuid(key);
		if(loggedInUser==null) {
			throw new UserException("Please provide a valid key to view bus");
		}
		Optional<Bus> opt=	busdao.findById(busId);
		if(opt.isPresent()) {
			Bus exbus=opt.get();
				
				return exbus;
			}
		throw new BusException("bus doesn't exists with this "+busId+" id");
	}


	@Override
	public List<Bus> viewBusByType(String busType, String key) throws BusException, UserException {
		CurrentUserSession loggedInUser=srepo.findByUuid(key);
		if(loggedInUser==null) {
			throw new UserException("Please provide a valid key to view buses");
		}
   List<Bus> bList = busdao.findByBusType(busType);
		
		if(bList.size()==0) {
			throw new BusException("bus list is empty");
		}

		return bList;
	}

	@Override
	public List<Bus> viewAllBus(String key) throws BusException, UserException{
		CurrentUserSession loggedInUser=srepo.findByUuid(key);
		if(loggedInUser==null) {
			throw new UserException("Please provide a valid key to view buses");
		}
		List<Bus> buslist=	busdao.findAll();
		if(buslist.size()==0) {
			throw new BusException("bus list is empty");
		}
		return buslist;
		
	}

}
