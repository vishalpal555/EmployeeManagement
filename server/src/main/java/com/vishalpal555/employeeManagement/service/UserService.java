package com.vishalpal555.employeeManagement.service;

import com.vishalpal555.employeeManagement.CustomException.UsernameAlreadyPresent;
import com.vishalpal555.employeeManagement.dto.UserDTO;
import com.vishalpal555.employeeManagement.pojo.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserService {
    public User getUser(String username) throws UsernameNotFoundException;
    public boolean isUsernamePresent(String username);
    public void saveNewUser(String username, String password) throws UsernameAlreadyPresent;
    public void saveUser(User user) throws UsernameAlreadyPresent;
    public List<UserDTO> getAllUsers();
}
