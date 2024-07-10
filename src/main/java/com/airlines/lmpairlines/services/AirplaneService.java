package com.airlines.lmpairlines.services;

import com.airlines.lmpairlines.dto.AirplaneDTO;
import com.airlines.lmpairlines.exceptions.NotFoundException;
import com.airlines.lmpairlines.model.entities.Airplane;
import com.airlines.lmpairlines.model.entities.FlightTicket;

public interface AirplaneService {
    Airplane insert(AirplaneDTO airplaneDTO) throws NotFoundException;
    Airplane findAirplaneById(Integer id) throws NotFoundException;
}
