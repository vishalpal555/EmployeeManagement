package com.vishalpal555.employeeManagement.repository;

import com.vishalpal555.employeeManagement.pojo.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VendorRepoInterface extends JpaRepository<Vendor, Long> {
    @Query(
            value = "SELECT * FROM vendors WHERE email=:email",
            nativeQuery = true
    )
    Optional<Vendor> findByEmail(@Param("email") String email);

    @Query(
            value = "DELETE FROM vendors WHERE email=:email",
            nativeQuery = true
    )
    boolean deleteByEmail(@Param("email") String email);
}
