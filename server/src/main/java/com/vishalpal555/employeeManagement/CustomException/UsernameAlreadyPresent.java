package com.vishalpal555.employeeManagement.CustomException;

public class UsernameAlreadyPresent extends Exception {
    public UsernameAlreadyPresent(String message) {
        super("User already present in the database" +message);
    }
}
