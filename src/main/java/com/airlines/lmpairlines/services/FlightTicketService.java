package com.airlines.lmpairlines.services;

import com.airlines.lmpairlines.dto.FilterDTO;
import com.airlines.lmpairlines.dto.FlightTicketDTO;
import com.airlines.lmpairlines.exceptions.NotFoundException;
import com.airlines.lmpairlines.model.entities.FlightTicket;
import org.springframework.data.domain.Page;
import java.util.List;


public interface FlightTicketService {
    FlightTicket insert(FlightTicketDTO flightTicketDTO) throws NotFoundException;
    FlightTicket findFlightTicketById(Integer id) throws NotFoundException;
    Page<List<FlightTicket>> filterFlightTickets(FilterDTO filterDTO);
}
