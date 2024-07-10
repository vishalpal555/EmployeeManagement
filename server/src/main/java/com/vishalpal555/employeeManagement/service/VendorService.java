package com.vishalpal555.employeeManagement.service;

import com.vishalpal555.employeeManagement.CustomException.UpiIdAlreadyPresent;
import com.vishalpal555.employeeManagement.CustomException.UsernameAlreadyPresent;
import com.vishalpal555.employeeManagement.pojo.Vendor;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;

public interface VendorService {
    public boolean isVendorPresent(String email);
    public boolean isUpiIdPresent(String upiId);
    public void saveVendor(Vendor vendor) throws UsernameAlreadyPresent, UpiIdAlreadyPresent;
    public List<Vendor> getAllVendors();
    public boolean deleteVendor(String email);
    public Optional<Vendor> getVendorByEmail(String email);
    public Optional<Vendor> getVendorById(Long id);
}
