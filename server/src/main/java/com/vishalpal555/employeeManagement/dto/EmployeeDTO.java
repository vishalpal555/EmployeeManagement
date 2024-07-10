package com.vishalpal555.employeeManagement.dto;

import com.vishalpal555.employeeManagement.pojo.Designation;
import com.vishalpal555.employeeManagement.pojo.Employee;
import jakarta.persistence.*;

public class EmployeeDTO {
    private String email;
    private String firstName;
    private String lastName;
    private Designation designation;
    private Long ctc;

    public EmployeeDTO() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Designation getDesignation() {
        return designation;
    }

    public void setDesignation(Designation designation) {
        this.designation = designation;
    }

    public Long getCtc() {
        return ctc;
    }

    public void setCtc(Long ctc) {
        this.ctc = ctc;
    }

    @Override
    public String toString() {
        return "Employee{" +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", designation=" + designation +
                ", ctc=" + ctc +
                '}';
    }
}
