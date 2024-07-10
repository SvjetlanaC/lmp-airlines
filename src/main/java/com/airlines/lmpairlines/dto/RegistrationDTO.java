package com.airlines.lmpairlines.dto;

import lombok.Data;

@Data
public class RegistrationDTO {
    private Integer customerId;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String address;
    private String country;
    private String city;
    private String role;
}
