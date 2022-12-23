package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.BusException;
import com.masai.exception.RouteException;
import com.masai.exception.UserException;
import com.masai.model.Bus;
import com.masai.model.CurrentUserSession;
import com.masai.model.Route;
import com.masai.repository.BusDao;
import com.masai.repository.RouteRepo;
import com.masai.repository.SessionRepo;

//this method is implementing bus service interface
@Service
public class BusServiceImpl implements BusService {

	@Autowired
	private BusDao busdao;

	@Autowired
	private SessionRepo srepo;

	@Autowired
	RouteRepo rrepo;

//	overriding method implement add bus method
	@Override
	public Bus addBus(Bus bus, String key) throws BusException, UserException {

		CurrentUserSession loggedInUser = srepo.findByUuid(key);

//		if user is not login it will throw exception
		if (loggedInUser == null) {
			throw new UserException("Please provide a valid key to add Bus");
		}

//		if user is Admin then you are allowed to add bus

		if (loggedInUser.getType().equalsIgnoreCase("Admin")) {

//			finding route is present or not
			Route route = rrepo.findByRouteFromAndRouteTo(bus.getRouteForm(), bus.getRouteTo());

			if (route != null) {
//				if contains bus with same details throw bus exception
				if (route.getBuslist().contains(bus)) {
					throw new BusException("Bus already exists");
				}

//				if not present then add bus to buslist
				route.getBuslist().add(bus);
				bus.setRoute(route);
				return busdao.save(bus);
			} else
				throw new BusException("Cannot find route for adding Bus");

		} else
			throw new UserException("Unauthorized Access! Only Admin can add bus");

	}

//	overriding method implement add update bus method

	@Override
	public Bus updateBus(Bus bus, String key) throws BusException, UserException {

		CurrentUserSession loggedInUser = srepo.findByUuid(key);
		
//		If user not logged in throw User Exception
		if (loggedInUser == null) {
			throw new UserException("Please provide a valid key to update Bus");
		}
		if (loggedInUser.getType().equalsIgnoreCase("Admin")) {
			Optional<Bus> opt = busdao.findById(bus.getBusId());
			if (opt.isPresent()) {
				Bus curr = opt.get();
//			if seat is not available you cannot update bus details	
				if (curr.getAvailabeSeats() != curr.getSeats())
					throw new BusException("Cannot update Bus already scheduled");

				Route route = rrepo.findByRouteFromAndRouteTo(curr.getRouteForm(), curr.getRouteTo());

				if (bus.getRouteForm() != null && bus.getRouteTo() != null) {
					route = rrepo.findByRouteFromAndRouteTo(bus.getRouteForm(), bus.getRouteTo());

//		if route not found it will throw bus exception			
					if (route == null)
						throw new BusException("Invalid route details");
				}
//		setting details	
				if (bus.getArrivalTime() != null)
					curr.setArrivalTime(bus.getArrivalTime().toString());
				if (bus.getAvailabeSeats() != null)
					curr.setAvailabeSeats(bus.getAvailabeSeats());
				if (bus.getBusName() != null)
					curr.setBusName(bus.getBusName());
				if (bus.getBusType() != null)
					curr.setBusType(bus.getBusType());
				if (bus.getDepartureTime() != null)
					curr.setDepartureTime(bus.getDepartureTime().toString());
				if (bus.getDriverName() != null)
					curr.setDriverName(bus.getDriverName());
				if (bus.getRouteForm() != null)
					curr.setRouteForm(bus.getRouteForm());
				if (bus.getRouteTo() != null)
					curr.setRouteTo(bus.getRouteTo());
				if (bus.getSeats() != null)
					curr.setSeats(bus.getSeats());

//			save updated bus 	
				Bus updated = busdao.save(curr);
				route.getBuslist().add(updated);
				route.getBuslist().remove(bus);

				return updated;

			}
			throw new BusException("Bus with id " + bus.getBusId() + "does not exist");

		} else
			throw new UserException("Unauthorized Access! Only Admin can make changes");

	}
//	overriding method implement delete bus method

	@Override
	public Bus deleteBus(Integer busId, String key) throws BusException, UserException {

		CurrentUserSession loggedInUser = srepo.findByUuid(key);
		
//		If user not logged in throw User Exception
		if (loggedInUser == null) {
			throw new UserException("Please provide a valid key to delete Bus");
		}
		if (loggedInUser.getType().equalsIgnoreCase("Admin")) {
//			optional is used to handle null pointer exception
			Optional<Bus> opt = busdao.findById(busId);
			if (opt.isPresent()) {
				Bus exbus = opt.get();

				if (exbus.getAvailabeSeats() != exbus.getSeats())
					throw new BusException("Cannot delete Bus already scheduled");

				busdao.delete(exbus);
				return exbus;
			}

			throw new BusException("bus doesn't exists with this " + busId + " id");
		} else
			throw new UserException("Unauthorized Access! Only Admin can delete Bus");

	}

//	overriding method implement  view by bus id  method
	@Override
	public Bus viewBus(Integer busId, String key) throws BusException, UserException {

		CurrentUserSession loggedInUser = srepo.findByUuid(key);
		
//		If user not logged in throw User Exception
		if (loggedInUser == null) {
			throw new UserException("Please provide a valid key to view bus");
		}
		Optional<Bus> opt = busdao.findById(busId);
		if (opt.isPresent()) {
			Bus exbus = opt.get();

			return exbus;
		}
		throw new BusException("Bus doesn't exists with this " + busId + " id");
	}

//	overriding method implement view all bus method
	@Override
	public List<Bus> viewBusByType(String busType, String key) throws BusException, UserException {
		CurrentUserSession loggedInUser = srepo.findByUuid(key);

//	If user not logged in throw User Exception
		if (loggedInUser == null) {
			throw new UserException("Please provide a valid key to view buses");
		}

//		finding list of bus by bus type
		List<Bus> bList = busdao.findByBusType(busType);

		if (bList.size() == 0) {
			throw new BusException("bus list is empty");
		}

		return bList;
	}

	@Override
	public List<Bus> viewAllBus(String key) throws BusException, UserException {
		CurrentUserSession loggedInUser = srepo.findByUuid(key);

//		If user not logged in throw User Exception
		if (loggedInUser == null) {
			throw new UserException("Please provide a valid key to view buses");
		}

//		anyone can view bus but it must be user or admin
		List<Bus> buslist = busdao.findAll();

		if (buslist.size() == 0) {
			throw new BusException("bus list is empty");
		}
		return buslist;

	}

}
