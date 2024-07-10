package com.vishalpal555.employeeManagement.service;

import com.vishalpal555.employeeManagement.pojo.EmailsSent;
import jakarta.mail.MessagingException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EmailService {
    public void sendEmail(String to, String subject, String body) throws MessagingException;
    public ResponseEntity<Object> sendAndSaveEmail(String to, String subject, String body);
    public ResponseEntity<List<EmailsSent>> getAllMails();
    public ResponseEntity<List<EmailsSent>> getMailByRecipient(String recipient);
    public ResponseEntity<List<EmailsSent>> getMailBySubject(String subject);
    public ResponseEntity<EmailsSent> getMailById(Long emailId);
}
