package com.vishalpal555.employeeManagement.service;

import com.vishalpal555.employeeManagement.CustomException.UsernameAlreadyPresent;
import com.vishalpal555.employeeManagement.pojo.Employee;

import java.util.List;

public interface EmployeeService {
    public boolean isEmployeePresent(String email);
    public void saveEmployee(Employee employee) throws UsernameAlreadyPresent;
    public List<Employee> getAllEmployee();
    public boolean deleteEmployee(String email);
}
