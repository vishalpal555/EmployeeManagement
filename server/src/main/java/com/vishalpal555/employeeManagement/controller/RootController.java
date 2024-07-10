package com.vishalpal555.employeeManagement.controller;

import com.vishalpal555.employeeManagement.CustomException.UsernameAlreadyPresent;
import com.vishalpal555.employeeManagement.config.Constants;
import com.vishalpal555.employeeManagement.config.SpringSecurity;
import com.vishalpal555.employeeManagement.dto.LoginRequestDTO;
import com.vishalpal555.employeeManagement.dto.SendEmailRequestDTO;
import com.vishalpal555.employeeManagement.dto.UserDTO;
import com.vishalpal555.employeeManagement.service.EmailService;
import com.vishalpal555.employeeManagement.service.JwtService;
import com.vishalpal555.employeeManagement.service.UserService;
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
import com.vishalpal555.employeeManagement.dto.RegisterRequestDTO;

import java.util.List;
import java.util.regex.Pattern;

@RestController
@RequestMapping
public class RootController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RootController.class);
    private static final Pattern EMAIL_PATTERN = Pattern.compile(Constants.EMAIL_PATTERN_REGEX);
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private SpringSecurity springSecurity;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private EmailService emailService;

    @PostMapping(path = "/register")
    public ResponseEntity<Object> register(@RequestBody RegisterRequestDTO registerRequestDTO) {
        ResponseEntity<Object> responseEntity;
        try {
            if(registerRequestDTO != null && registerRequestDTO.getPassword() != null && registerRequestDTO.getConfirmPassword() != null) {
                if(registerRequestDTO.getPassword().length() >= 8 && registerRequestDTO.getPassword().equals(registerRequestDTO.getConfirmPassword())) {
                    if(EMAIL_PATTERN.matcher(registerRequestDTO.getUsername()).matches()) {
                        userService.saveNewUser(registerRequestDTO.getUsername(), registerRequestDTO.getPassword());
                        responseEntity = ResponseEntity.ok().build();
                    } else {
                        responseEntity = ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body("Email type is wrong");
                    }
                } else {
                    responseEntity = ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body("Password Doesn't match requirement");
                }
            } else {
                LOGGER.error("Handled request body error registerRequestDTO: {}", registerRequestDTO);
                responseEntity = ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).build();
            }
        } catch (UsernameAlreadyPresent usernameAlreadyPresent){
            LOGGER.error("handled usernameAlreadyPresent ", usernameAlreadyPresent);
            responseEntity = ResponseEntity.status(HttpStatus.CONFLICT).body("User already present with this username");
        } catch (Exception e) {
            LOGGER.error("handled exception ", e);
            responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return responseEntity;
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequestDTO loginRequestDTO){
        ResponseEntity<Object> responseEntity;
        try{
            if(loginRequestDTO != null && loginRequestDTO.getUsername() != null && loginRequestDTO.getPassword() != null){
                LOGGER.info("called /login requestBody: {}", loginRequestDTO);
                String s = springSecurity.passwordEncoder().encode(loginRequestDTO.getPassword());
                UserDetails userDetails = userService.loadUserByUsername(loginRequestDTO.getUsername());
                if(springSecurity.passwordEncoder().matches(loginRequestDTO.getPassword(), userDetails.getPassword())) {
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(), loginRequestDTO.getPassword()));
                    final String jwt = jwtService.generateToken(userService.loadUserByUsername(loginRequestDTO.getUsername()));
                    responseEntity = ResponseEntity.ok(jwt);
                } else {
                    responseEntity = ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
                }
            } else {
                responseEntity = ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).build();
            }
        } catch (UsernameNotFoundException usernameNotFoundException){
            LOGGER.error("handled usernameNotFoundException ", usernameNotFoundException);
            responseEntity = ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (Exception e){
            LOGGER.error("handled exception ", e);
            responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return responseEntity;
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<Object> getAllUsers(){
        try {
            LOGGER.info("called /getAllUsers");
            List<UserDTO> userDTOList = userService.getAllUsers();
            LOGGER.info("resp {}", userDTOList);
            return ResponseEntity.status(HttpStatus.OK).body(userDTOList);
        } catch (Exception e){
            LOGGER.error("handled exception ", e);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @PostMapping("/sendMail")
    public ResponseEntity<Object> sendMail(@RequestBody SendEmailRequestDTO sendEmailRequestDTO){
        try {
            LOGGER.info("called /sendMail requestBody: {}", sendEmailRequestDTO.toString());
            return emailService.sendAndSaveEmail(sendEmailRequestDTO.getToEmail(), sendEmailRequestDTO.getSubject(), sendEmailRequestDTO.getBody());
        } catch (Exception e){
            LOGGER.error("handled exception ", e);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

}
