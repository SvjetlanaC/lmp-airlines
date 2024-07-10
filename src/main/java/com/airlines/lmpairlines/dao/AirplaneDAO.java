package com.airlines.lmpairlines.dao;

import com.airlines.lmpairlines.model.entities.Airplane;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirplaneDAO extends JpaRepository<Airplane,Integer> {
}
