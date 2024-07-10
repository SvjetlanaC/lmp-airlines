package com.airlines.lmpairlines.services;

import com.airlines.lmpairlines.dto.AirCompanyDTO;
import com.airlines.lmpairlines.exceptions.NotFoundException;
import com.airlines.lmpairlines.model.entities.AirCompany;

public interface AirCompanyService {
    AirCompany insert(AirCompanyDTO airCompanyDTO) throws NotFoundException;
    AirCompany findAirCompanyById(Integer id) throws NotFoundException;
}
