package com.vishalpal555.employeeManagement.dto;

import com.vishalpal555.employeeManagement.pojo.Vendor;
import jakarta.persistence.*;

public class VendorDTO {
    private String email;
    private String name;
    private String upiId;

    public VendorDTO() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUpiId() {
        return upiId;
    }

    public void setUpiId(String upiId) {
        this.upiId = upiId;
    }

    @Override
    public String toString() {
        return "Vendor{" +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", upiId='" + upiId + '\'' +
                '}';
    }
}
