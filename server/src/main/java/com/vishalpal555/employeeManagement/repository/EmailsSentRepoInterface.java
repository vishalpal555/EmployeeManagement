package com.vishalpal555.employeeManagement.repository;

import com.vishalpal555.employeeManagement.pojo.EmailsSent;
import com.vishalpal555.employeeManagement.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmailsSentRepoInterface extends JpaRepository<EmailsSent, Long> {
    @Query(
            value = "SELECT * FROM users WHERE users.to_email=:to_email",
            nativeQuery = true
    )
    List<EmailsSent> findEmailByRecipient(@Param("to_email") String toEmail);
}
