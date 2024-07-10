package com.vishalpal555.employeeManagement.service;

import jakarta.mail.MessagingException;
import org.springframework.http.ResponseEntity;

public interface EmailService {
    public void sendEmail(String to, String subject, String body) throws MessagingException;
    public ResponseEntity<Object> sendAndSaveEmail(String to, String subject, String body);
}
