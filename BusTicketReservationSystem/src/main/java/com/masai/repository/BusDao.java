package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.model.Bus;

@Repository
public interface BusDao extends JpaRepository<Bus, Integer> {
	
	

}
