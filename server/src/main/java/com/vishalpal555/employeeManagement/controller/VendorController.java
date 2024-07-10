package com.vishalpal555.employeeManagement.controller;

import com.vishalpal555.employeeManagement.CustomException.UsernameAlreadyPresent;
import com.vishalpal555.employeeManagement.config.Constants;
import com.vishalpal555.employeeManagement.config.SpringSecurity;
import com.vishalpal555.employeeManagement.dto.LoginRequestDTO;
import com.vishalpal555.employeeManagement.dto.RegisterRequestDTO;
import com.vishalpal555.employeeManagement.dto.SendEmailRequestDTO;
import com.vishalpal555.employeeManagement.dto.UserDTO;
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

import java.util.List;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/vendor")
public class VendorController {
    private static final Logger LOGGER = LoggerFactory.getLogger(VendorController.class);

}
