package com.vishalpal555.employeeManagement.service;

import com.vishalpal555.employeeManagement.CustomException.UsernameAlreadyPresent;
import com.vishalpal555.employeeManagement.pojo.Vendor;

import java.util.List;

public interface VendorService {
    public boolean isVendorPresent(String email);
    public void saveVendor(Vendor vendor) throws UsernameAlreadyPresent;
    public List<Vendor> getAllVendors();
    public boolean deleteVendor(String email);
}
