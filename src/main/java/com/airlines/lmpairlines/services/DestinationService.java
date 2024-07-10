package com.airlines.lmpairlines.services;

import com.airlines.lmpairlines.dao.DestinationDAO;
import com.airlines.lmpairlines.dto.DestinationDTO;
import com.airlines.lmpairlines.exceptions.NotFoundException;
import com.airlines.lmpairlines.model.entities.Destination;

public interface DestinationService {
    Destination insert(DestinationDTO destinationDTO) throws NotFoundException;
    Destination findDestinationById(Integer id) throws NotFoundException;
}
