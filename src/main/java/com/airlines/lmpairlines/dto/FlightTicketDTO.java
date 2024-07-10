package com.airlines.lmpairlines.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class FlightTicketDTO {
    private Date dateOfCreation;
    private String username;
    private Integer reservedSeats;
    private String reservationStatus;
    private FlightDTO flightDTO;
}
