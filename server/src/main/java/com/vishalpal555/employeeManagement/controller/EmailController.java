package com.vishalpal555.employeeManagement.controller;

import com.vishalpal555.employeeManagement.CustomException.UsernameAlreadyPresent;
import com.vishalpal555.employeeManagement.config.Constants;
import com.vishalpal555.employeeManagement.config.SpringSecurity;
import com.vishalpal555.employeeManagement.dto.LoginRequestDTO;
import com.vishalpal555.employeeManagement.dto.RegisterRequestDTO;
import com.vishalpal555.employeeManagement.dto.SendEmailRequestDTO;
import com.vishalpal555.employeeManagement.dto.UserDTO;
import com.vishalpal555.employeeManagement.pojo.EmailsSent;
import com.vishalpal555.employeeManagement.service.EmailService;
import com.vishalpal555.employeeManagement.service.JwtService;
import com.vishalpal555.employeeManagement.service.implementation.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/mail")
public class EmailController {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailController.class);
    private static final Pattern EMAIL_PATTERN = Pattern.compile(Constants.EMAIL_PATTERN_REGEX);
    @Autowired
    private EmailService emailService;

    @PostMapping("/sendMail")
    public ResponseEntity<Object> sendMail(@RequestBody SendEmailRequestDTO sendEmailRequestDTO){
        try {
            LOGGER.info("called /mail/sendMail requestBody: {}", sendEmailRequestDTO.toString());
            if(EMAIL_PATTERN.matcher(sendEmailRequestDTO.getToEmail()).matches()) {
                return emailService.sendAndSaveEmail(sendEmailRequestDTO.getToEmail(), sendEmailRequestDTO.getSubject(), sendEmailRequestDTO.getBody());
            } else {
                return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body("Email type is wrong");
            }
        } catch (Exception e){
            LOGGER.error("handled exception ", e);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @GetMapping
    public ResponseEntity<?> getMails(@RequestParam(required = false) String recipient, @RequestParam(required = false) Long id){
        try {
            LOGGER.info("called /mail?recipient={}", recipient);
            if(recipient == null && id == null){
                return emailService.getAllMails();
            } else if(recipient != null && id != null){
                ResponseEntity<List<EmailsSent>> emailsSentListResp = emailService.getMailByRecipient(recipient);
                ResponseEntity<EmailsSent> emailByIdResp = emailService.getMailById(id);
                List<EmailsSent> emailsSentList = new ArrayList<>();
                if(emailsSentListResp.getStatusCode() == HttpStatus.OK) {
                    emailsSentList.addAll(Objects.requireNonNullElse(emailsSentListResp.getBody(), new ArrayList<>()));
                }
                if(emailByIdResp.getStatusCode() == HttpStatus.OK){
                    emailsSentList.add(emailByIdResp.getBody());
                }
                return ResponseEntity.ok(emailsSentList);
            } else if (recipient != null){
                return emailService.getMailByRecipient(recipient);
            } else {
                return emailService.getMailById(id);
            }
        } catch (Exception e){
            LOGGER.error("handled exception ", e);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

}
