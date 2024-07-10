package com.airlines.lmpairlines.dao;

import com.airlines.lmpairlines.model.entities.AirCompany;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirCompanyDAO extends JpaRepository<AirCompany,Integer> {
}
