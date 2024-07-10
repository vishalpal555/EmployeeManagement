package com.vishalpal555.employeeManagement.dto;

import com.vishalpal555.employeeManagement.pojo.User;
import com.vishalpal555.employeeManagement.pojo.UserRole;

public class UserDTO {
    private String username;
    private UserRole userRole;
    public UserDTO(String username, UserRole userRole) {
        this.username = username;
        this.userRole = userRole;
    }
    public static UserDTO toUserDTO(User user){
        return new UserDTO(user.getUsername(), user.getUserRole());
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "username='" + username + '\'' +
                ", userRole=" + userRole +
                '}';
    }
}
