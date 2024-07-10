package com.airlines.lmpairlines.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@Data
@Entity
@Table(name = "role", schema = "public", catalog = "LanacoAirlinesSpringApp")
public class Role {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "role_id")
    private Integer roleId;
    @Basic
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "role")
    @JsonIgnore
    private List<Customer> customers;
}