package com.airlines.lmpairlines.dao;

import com.airlines.lmpairlines.model.entities.Destination;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DestinationDAO extends JpaRepository<Destination,Integer> {
}
