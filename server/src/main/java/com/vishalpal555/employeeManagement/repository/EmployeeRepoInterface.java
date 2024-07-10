package com.vishalpal555.employeeManagement.repository;

import com.vishalpal555.employeeManagement.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VendorRepoInterface extends JpaRepository<User, Long> {
    @Query(
            value = "SELECT * FROM users WHERE username=:username",
            nativeQuery = true
    )
    Optional<User> findByUsername(@Param("username") String username);
}
