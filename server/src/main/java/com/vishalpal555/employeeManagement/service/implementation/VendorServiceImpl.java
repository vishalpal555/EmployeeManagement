package com.vishalpal555.employeeManagement.service.implementation;

import com.vishalpal555.employeeManagement.CustomException.UpiIdAlreadyPresent;
import com.vishalpal555.employeeManagement.CustomException.UsernameAlreadyPresent;
import com.vishalpal555.employeeManagement.pojo.Employee;
import com.vishalpal555.employeeManagement.pojo.Vendor;
import com.vishalpal555.employeeManagement.repository.VendorRepoInterface;
import com.vishalpal555.employeeManagement.service.VendorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class VendorServiceImpl implements VendorService {
    private static final Logger LOGGER = LoggerFactory.getLogger(VendorServiceImpl.class);

    @Autowired
    private VendorRepoInterface vendorRepoInterface;

    @Override
    public boolean isVendorPresent(String email) {
        return vendorRepoInterface.findByEmail(email).isPresent();
    }

    @Override
    public boolean isUpiIdPresent(String upiId) {
        return vendorRepoInterface.findByUpiId(upiId).isPresent();
    }

    @Override
    public void saveVendor(Vendor vendor) throws UsernameAlreadyPresent, UpiIdAlreadyPresent {
        if(this.isVendorPresent(vendor.getEmail())){
            throw new UsernameAlreadyPresent(vendor.getEmail());
        } else if (isUpiIdPresent(vendor.getUpiId())) {
            throw new UpiIdAlreadyPresent(vendor.getUpiId());
        } else {
            vendorRepoInterface.save(vendor);
        }
    }

    @Override
    public List<Vendor> getAllVendors() {
        return vendorRepoInterface.findAll();
    }

    @Override
    public boolean deleteVendor(String email) {
        return vendorRepoInterface.deleteByEmail(email);
    }

    @Override
    public Optional<Vendor> getVendorByEmail(String email){
        return vendorRepoInterface.findByEmail(email);
    }

    @Override
    public Optional<Vendor> getVendorById(Long id){
        return vendorRepoInterface.findById(id);
    }
}
