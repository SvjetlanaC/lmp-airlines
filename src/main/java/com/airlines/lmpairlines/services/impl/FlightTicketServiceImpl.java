package com.airlines.lmpairlines.services.impl;

import com.airlines.lmpairlines.dao.CustomerDAO;
import com.airlines.lmpairlines.dao.FlightTicketDAO;
import com.airlines.lmpairlines.dto.FilterDTO;
import com.airlines.lmpairlines.dto.FlightTicketDTO;
import com.airlines.lmpairlines.exceptions.NotFoundException;
import com.airlines.lmpairlines.model.entities.Customer;
import com.airlines.lmpairlines.model.entities.Flight;
import com.airlines.lmpairlines.model.entities.FlightTicket;
import com.airlines.lmpairlines.services.AirplaneService;
import com.airlines.lmpairlines.services.FlightService;
import com.airlines.lmpairlines.services.FlightTicketService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class FlightTicketServiceImpl implements FlightTicketService {

    @Autowired
    private FlightTicketDAO flightTicketDAO;
    @Autowired
    private FlightService flightService;
    @Autowired
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private CustomerDAO customerDAO;

    @Override
    public FlightTicket insert(FlightTicketDTO flightTicketDTO) throws NotFoundException {
        Flight flight=flightService.insert(flightTicketDTO.getFlightDTO());
        Customer customer=customerDAO.findCustomerByUsername(flightTicketDTO.getUsername());
        if(customer==null){
            throw new NotFoundException();
        }
        FlightTicket flightTicket=new FlightTicket();
        flightTicket.setCustomer(customer);
        flightTicket.setFlight(flight);
        flightTicket.setDateOfCreation(flightTicketDTO.getDateOfCreation());
        flightTicket.setReservedSeats(flightTicketDTO.getReservedSeats());
        flightTicket.setReservationStatus(flightTicketDTO.getReservationStatus());
        flightTicket=flightTicketDAO.saveAndFlush(flightTicket);
        entityManager.refresh(flightTicket);
        return findFlightTicketById(flightTicket.getFlightTicketId());
    }

    @Override
    public FlightTicket findFlightTicketById(Integer id) throws NotFoundException {
        return flightTicketDAO.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public Page<List<FlightTicket>> filterFlightTickets(FilterDTO filterDTO) {
        return flightTicketDAO.findFlightTicketsByCustomer_Username(filterDTO.getFilter(),PageRequest.of(filterDTO.getPageNumber(), filterDTO.getPageSize()));
    }
}
