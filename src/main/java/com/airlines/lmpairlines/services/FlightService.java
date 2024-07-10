package com.airlines.lmpairlines.services;

import com.airlines.lmpairlines.dto.FlightDTO;
import com.airlines.lmpairlines.exceptions.NotFoundException;
import com.airlines.lmpairlines.model.entities.Flight;

public interface FlightService {
    Flight insert(FlightDTO flightDTO) throws NotFoundException;
    Flight findFlightById(Integer id) throws NotFoundException;
}
