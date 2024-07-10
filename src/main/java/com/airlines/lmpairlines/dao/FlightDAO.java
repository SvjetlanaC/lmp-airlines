package com.airlines.lmpairlines.dao;

import com.airlines.lmpairlines.model.entities.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightDAO extends JpaRepository<Flight,Integer> {
}
