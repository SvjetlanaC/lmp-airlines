package com.airlines.lmpairlines.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class FlightDTO {
    private AirplaneDTO airplaneDTO;
    private AirCompanyDTO airCompanyDTO;
    private String destination;
    private Integer numberOfSeats;
    private Integer availableSeats;
    private Date flightDate;
    private Double price;
}
