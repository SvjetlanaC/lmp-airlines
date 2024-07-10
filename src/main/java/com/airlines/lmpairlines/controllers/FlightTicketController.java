package com.airlines.lmpairlines.controllers;

import com.airlines.lmpairlines.dto.FilterDTO;
import com.airlines.lmpairlines.dto.FlightTicketDTO;
import com.airlines.lmpairlines.exceptions.NotFoundException;
import com.airlines.lmpairlines.model.entities.FlightTicket;
import com.airlines.lmpairlines.services.FlightTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/flightTickets")
public class FlightTicketController {
    @Autowired
    private FlightTicketService flightTicketService;
    @PostMapping
    public FlightTicket reserveTicket(@RequestBody FlightTicketDTO flightTicketDTO) throws NotFoundException {
        return flightTicketService.insert(flightTicketDTO);
    }

    @PostMapping("/filter")
    public Page<List<FlightTicket>> reserveTicket(@RequestBody FilterDTO filterDTO) {
        return flightTicketService.filterFlightTickets(filterDTO);
    }
}
