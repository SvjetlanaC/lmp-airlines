package com.airlines.lmpairlines.services.impl;

import com.airlines.lmpairlines.dao.FlightDAO;
import com.airlines.lmpairlines.dto.DestinationDTO;
import com.airlines.lmpairlines.dto.FlightDTO;
import com.airlines.lmpairlines.exceptions.NotFoundException;
import com.airlines.lmpairlines.model.entities.AirCompany;
import com.airlines.lmpairlines.model.entities.Airplane;
import com.airlines.lmpairlines.model.entities.Destination;
import com.airlines.lmpairlines.model.entities.Flight;
import com.airlines.lmpairlines.services.AirCompanyService;
import com.airlines.lmpairlines.services.AirplaneService;
import com.airlines.lmpairlines.services.DestinationService;
import com.airlines.lmpairlines.services.FlightService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class FlightServiceImpl implements FlightService {
    @Autowired
    private FlightDAO flightDAO;
    @Autowired
    private AirplaneService airplaneService;
    @Autowired
    private AirCompanyService airCompanyService;
    @Autowired
    private DestinationService destinationService;
    @Autowired
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public Flight insert(FlightDTO flightDTO) throws NotFoundException {
        Airplane airplane=airplaneService.insert(flightDTO.getAirplaneDTO());
        AirCompany airCompany=airCompanyService.insert(flightDTO.getAirCompanyDTO());
        Flight flight=new Flight();
        flight.setFlightDate(flightDTO.getFlightDate());
        flight.setAirplane(airplane);
        flight.setAirCompany(airCompany);
        flight.setPrice(flightDTO.getPrice());
        flight.setAvailableSeats(flightDTO.getAvailableSeats());
        flight.setNumberOfSeats(flightDTO.getNumberOfSeats());
        Destination destination=destinationService.insert(new DestinationDTO(flightDTO.getDestination()));
        flight.setDestination(destination);
        flight=flightDAO.saveAndFlush(flight);
        entityManager.refresh(flight);
        return findFlightById(flight.getFlightId());
    }

    @Override
    public Flight findFlightById(Integer id) throws NotFoundException {
        return flightDAO.findById(id).orElseThrow(NotFoundException::new);
    }
}
