package com.vishalpal555.employeeManagement.controller;

import com.vishalpal555.employeeManagement.service.VendorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/vendor")
public class VendorController {
    private static final Logger LOGGER = LoggerFactory.getLogger(VendorController.class);

    @Autowired
    private VendorService vendorService;

    @GetMapping
    public ResponseEntity<?> getVendors(@RequestParam(required = false) String email, @RequestParam(required = false) Long id){
        try {
            LOGGER.info("called /vendor email:{}, id:{}", Objects.requireNonNullElse(email, "PARAM_MISSING"), Objects.requireNonNullElse(id, "PARAM_MISSING"));
            if(email == null && id == null){
                return ResponseEntity.ok(vendorService.getAllVendors());
            } else if (email != null){
                return vendorService.getVendorByEmail(email)
                        .map(ResponseEntity::ok)
                        .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());

            } else {
                return vendorService.getVendorById(id)
                        .map(ResponseEntity::ok)
                        .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
            }
        } catch (Exception e){
            LOGGER.error("handled exception ", e);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

}
