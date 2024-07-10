package com.airlines.lmpairlines.services;

import jakarta.mail.MessagingException;


public interface EmailService {
    void sendEmail(String to,String body) throws MessagingException;
}
