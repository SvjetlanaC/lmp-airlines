package com.airlines.lmpairlines.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Data
@Entity
@Table(name = "airplane", schema = "public", catalog = "LanacoAirlinesSpringApp")
public class Airplane {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "airplane_id")
    private Integer airplaneId;
    @Basic
    @Column(name = "seats")
    private Integer seats;
    @Basic
    @Column(name = "brand")
    private String brand;
    @OneToMany(mappedBy = "airplane")
    @JsonIgnore
    private List<Flight> flights;
}
