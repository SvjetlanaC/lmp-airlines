package com.airlines.lmpairlines.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
@Entity
@Table(name = "flight", schema = "public", catalog = "LanacoAirlinesSpringApp")
public class Flight {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "flight_id")
    private Integer flightId;
    @Basic
    @Column(name = "number_of_seats")
    private Integer numberOfSeats;
    @Basic
    @Column(name = "available_seats")
    private Integer availableSeats;
    @Basic
    @Column(name = "price")
    private Double price;
    @Basic
    @Column(name = "flight_date")
    private Date flightDate;
    @ManyToOne
    @JoinColumn(name = "airplane_id", referencedColumnName = "airplane_id", nullable = false)
    private Airplane airplane;
    @ManyToOne
    @JoinColumn(name = "air_company_id", referencedColumnName = "air_company_id", nullable = false)
    private AirCompany airCompany;
    @ManyToOne
    @JoinColumn(name = "destination_id", referencedColumnName = "destination_id", nullable = false)
    private Destination destination;
    @OneToMany(mappedBy = "flight")
    @JsonIgnore
    private List<FlightTicket> flightTickets;
}