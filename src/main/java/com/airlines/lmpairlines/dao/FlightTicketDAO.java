package com.airlines.lmpairlines.dao;

import com.airlines.lmpairlines.model.entities.FlightTicket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlightTicketDAO extends JpaRepository<FlightTicket,Integer> {
    Page<List<FlightTicket>> findFlightTicketsByCustomer_Username(String username, Pageable pageable);
}
