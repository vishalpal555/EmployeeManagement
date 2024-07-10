package com.vishalpal555.employeeManagement.controller;

import com.vishalpal555.employeeManagement.pojo.EmailsSent;
import com.vishalpal555.employeeManagement.pojo.Employee;
import com.vishalpal555.employeeManagement.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<?> getEmployees(@RequestParam(required = false) String email, @RequestParam(required = false) Long id){
        try {
            LOGGER.info("called /employee email:{}, id:{}", Objects.requireNonNullElse(email, "PARAM_MISSING"), Objects.requireNonNullElse(id, "PARAM_MISSING"));
            if(email == null && id == null){
                return ResponseEntity.ok(employeeService.getAllEmployee());
            } else if (email != null){
                return employeeService.getEmployeeByEmail(email)
                                        .map(ResponseEntity::ok)
                                        .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());

            } else {
                return employeeService.getEmployeeById(id)
                        .map(ResponseEntity::ok)
                        .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
            }
        } catch (Exception e){
            LOGGER.error("handled exception ", e);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

}
