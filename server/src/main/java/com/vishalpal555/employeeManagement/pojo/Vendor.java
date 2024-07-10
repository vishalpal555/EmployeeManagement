package com.vishalpal555.employeeManagement.pojo;

import jakarta.persistence.*;

@Entity
@Table(name = "vendors")
public class Vendor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String email;
    @Column
    private String name;
    @Column(unique = true)
    private String upiId;

    public Vendor() {
    }

    public Vendor(String email, String name, String upiId) {
        this.email = email;
        this.name = name;
        this.upiId = upiId;
    }

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
                "id=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", upiId='" + upiId + '\'' +
                '}';
    }
}
