package com.airlines.lmpairlines.model.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
@Data
@Entity
@Table(name = "flight_ticket", schema = "public", catalog = "LanacoAirlinesSpringApp")
public class FlightTicket {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "flight_ticket_id")
    private Integer flightTicketId;
    @Basic
    @Column(name = "date_of_creation")
    private Date dateOfCreation;
    @Basic
    @Column(name = "reserved_seats")
    private Integer reservedSeats;
    @Basic
    @Column(name = "reservation_status")
    private String reservationStatus;
    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id", nullable = false)
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "flight_id", referencedColumnName = "flight_id", nullable = false)
    private Flight flight;
}