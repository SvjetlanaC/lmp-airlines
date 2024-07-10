package com.airlines.lmpairlines.controllers;

import com.airlines.lmpairlines.dto.LoginDTO;
import com.airlines.lmpairlines.dto.RegistrationDTO;
import com.airlines.lmpairlines.exceptions.NotFoundException;
import com.airlines.lmpairlines.exceptions.UsernameExistsException;
import com.airlines.lmpairlines.model.entities.Customer;
import com.airlines.lmpairlines.security.JwtUtils;
import com.airlines.lmpairlines.services.CustomerService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private JwtUtils jwtUtils;
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO){
        Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(),loginDTO.getPassword()));
        final UserDetails user=userDetailsService.loadUserByUsername(loginDTO.getUsername());
        if(authentication.isAuthenticated()){
            return ResponseEntity.ok(jwtUtils.generateToken(user));
        }
        return ResponseEntity.status(401).body(null);
//        return ResponseEntity.ok(jwtUtils.generateToken(user));
    }

    @PostMapping("/register")
    public Customer register(@RequestBody RegistrationDTO registrationDTO) throws NotFoundException, UsernameExistsException, MessagingException {

        return customerService.insert(registrationDTO);
    }
}
