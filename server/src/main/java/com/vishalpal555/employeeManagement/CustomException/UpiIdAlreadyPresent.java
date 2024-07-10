package com.vishalpal555.employeeManagement.CustomException;

public class UpiIdAlreadyPresent extends Exception {
    public UpiIdAlreadyPresent(String message) {
        super("UPI ID already present in the database: " +message);
    }
}
