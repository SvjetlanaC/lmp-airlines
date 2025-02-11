package com.airlines.lmpairlines.dao;

import com.airlines.lmpairlines.model.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerDAO extends JpaRepository<Customer,Integer> {
    Customer findCustomerByUsername(String username);
}
