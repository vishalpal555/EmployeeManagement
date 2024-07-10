package com.vishalpal555.employeeManagement.service.implementation;

import com.vishalpal555.employeeManagement.CustomException.UsernameAlreadyPresent;
import com.vishalpal555.employeeManagement.pojo.Employee;
import com.vishalpal555.employeeManagement.repository.EmployeeRepoInterface;
import com.vishalpal555.employeeManagement.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeRepoInterface employeeRepoInterface;

    @Override
    public boolean isEmployeePresent(String email) {
        return employeeRepoInterface.findByEmail(email).isPresent();
    }

    @Override
    public void saveEmployee(Employee employee) throws UsernameAlreadyPresent {
        if(this.isEmployeePresent(employee.getEmail())){
            throw new UsernameAlreadyPresent(employee.getEmail());
        } else {
            employeeRepoInterface.save(employee);
        }
    }

    @Override
    public List<Employee> getAllEmployee() {
        return employeeRepoInterface.findAll();
    }

    @Override
    public boolean deleteEmployee(String email) {
        return employeeRepoInterface.deleteByEmail(email);
    }

    @Override
    public Optional<Employee> getEmployeeByEmail(String email){
        return employeeRepoInterface.findByEmail(email);
    }

    @Override
    public Optional<Employee> getEmployeeById(Long id){
        return employeeRepoInterface.findById(id);
    }
}
