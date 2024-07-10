package com.vishalpal555.employeeManagement.repository;

import com.vishalpal555.employeeManagement.pojo.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepoInterface extends JpaRepository<Employee, Long> {
    @Query(
            value = "SELECT * FROM employees WHERE email=:email",
            nativeQuery = true
    )
    Optional<Employee> findByEmail(@Param("email") String email);

    @Query(
            value = "DELETE FROM employees WHERE email=:email",
            nativeQuery = true
    )
    boolean deleteByEmail(@Param("email") String email);
}
