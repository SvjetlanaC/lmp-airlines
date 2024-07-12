package com.airlines.lmpairlines.services.impl;

import com.airlines.lmpairlines.dao.CustomerDAO;
import com.airlines.lmpairlines.dto.RegistrationDTO;
import com.airlines.lmpairlines.exceptions.NotFoundException;
import com.airlines.lmpairlines.exceptions.UsernameExistsException;
import com.airlines.lmpairlines.model.entities.Customer;
import com.airlines.lmpairlines.model.entities.Role;
import com.airlines.lmpairlines.services.CustomerService;
import com.airlines.lmpairlines.services.EmailService;
import com.airlines.lmpairlines.services.RoleService;
import jakarta.mail.MessagingException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerDAO customerRepository;
    @Autowired
    private RoleService roleService;
    @Autowired
    private EmailService emailService;
    @PersistenceContext
    @Autowired
    private EntityManager entityManager;

    @Override
    public Customer insert(RegistrationDTO registrationDTO) throws UsernameExistsException, NotFoundException {


        Customer costumerEntity=customerRepository.findCustomerByUsername(registrationDTO.getUsername());
        if(costumerEntity!=null){
            throw new UsernameExistsException();
        }
        Customer customer=new Customer();
        Role role=roleService.findRoleByName(registrationDTO.getRole());
        if(role!=null){
            customer.setRole(role);
        }else{
            Role r=new Role();
            r.setRoleId(1);
            customer.setRole(r);
        }
        customer.setUsername(registrationDTO.getUsername());
        customer.setPassword(BCrypt.hashpw(registrationDTO.getPassword(), BCrypt.gensalt()));
        customer.setLastName(registrationDTO.getLastName());
        customer.setFirstName(registrationDTO.getFirstName());
        customer.setAddress(registrationDTO.getAddress());
        customer.setCity(registrationDTO.getCity());
        customer.setCountry(registrationDTO.getCountry());
        customer.setEmail(registrationDTO.getEmail());
        customer=customerRepository.saveAndFlush(customer);
        entityManager.refresh(customer);

        Customer newCustomer=findCustomerById(customer.getCustomerId());
        if(newCustomer!=null) {
            try {
                emailService.sendEmail(customer.getEmail(),customer.getUsername());
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }

        return newCustomer;
    }

    @Override
    public Customer findCustomerByUsername(String username) {
        return customerRepository.findCustomerByUsername(username);
    }

    @Override
    public Customer findCustomerById(Integer id) throws NotFoundException {
        return customerRepository.findById(id).orElseThrow(NotFoundException::new);
    }
}
