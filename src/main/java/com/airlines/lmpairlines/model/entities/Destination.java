package com.airlines.lmpairlines.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "destination", schema = "public", catalog = "LanacoAirlinesSpringApp")
public class Destination {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "destination_id")
    private Integer destinationId;
    @Basic
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "destination")
    @JsonIgnore
    private List<Flight> flights;
}
