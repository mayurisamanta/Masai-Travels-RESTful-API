package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.model.Route;

public interface RouteRepo extends JpaRepository<Route, Integer> {

	public Route findByRouteFromAndRouteTo(String from, String To);
	
}
