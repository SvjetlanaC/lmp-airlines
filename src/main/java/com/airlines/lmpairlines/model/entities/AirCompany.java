package com.airlines.lmpairlines.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "air_company", schema = "public", catalog = "LanacoAirlinesSpringApp")
public class AirCompany {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "air_company_id")
    private Integer airCompanyId;
    @Basic
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "airCompany")
    @JsonIgnore
    private List<Flight> flights;
}
