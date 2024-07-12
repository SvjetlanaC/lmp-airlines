package com.airlines.lmpairlines.services;

import com.airlines.lmpairlines.dto.LoginDTO;
import com.airlines.lmpairlines.dto.RegistrationDTO;
import com.airlines.lmpairlines.exceptions.NotFoundException;
import com.airlines.lmpairlines.exceptions.UsernameExistsException;
import com.airlines.lmpairlines.model.entities.Customer;
import jakarta.mail.MessagingException;
import jakarta.persistence.criteria.CriteriaBuilder;

public interface CustomerService {

    Customer insert(RegistrationDTO registrationDTO) throws UsernameExistsException, NotFoundException;
    Customer findCustomerByUsername(String username);
    Customer findCustomerById(Integer id) throws NotFoundException;
}
