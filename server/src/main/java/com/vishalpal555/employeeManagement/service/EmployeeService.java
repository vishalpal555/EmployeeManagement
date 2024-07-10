package com.vishalpal555.employeeManagement.service;

import com.vishalpal555.employeeManagement.CustomException.UsernameAlreadyPresent;
import com.vishalpal555.employeeManagement.pojo.Employee;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    public boolean isEmployeePresent(String email);
    public void saveEmployee(Employee employee) throws UsernameAlreadyPresent;
    public List<Employee> getAllEmployee();
    public boolean deleteEmployee(String email);
    public Optional<Employee> getEmployeeByEmail(String email);
    public Optional<Employee> getEmployeeById(Long id);
}
