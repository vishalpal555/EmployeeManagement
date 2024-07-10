package com.vishalpal555.employeeManagement.service.implementation;

import com.vishalpal555.employeeManagement.CustomException.UsernameAlreadyPresent;
import com.vishalpal555.employeeManagement.config.Constants;
import com.vishalpal555.employeeManagement.dto.UserDTO;
import com.vishalpal555.employeeManagement.pojo.User;
import com.vishalpal555.employeeManagement.pojo.UserRole;
import com.vishalpal555.employeeManagement.repository.UserRepoInterface;
import com.vishalpal555.employeeManagement.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepoInterface userRepoInterface;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepoInterface.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User " +username +" not found"));
    }

    @Override
    public User getUser(String username) throws UsernameNotFoundException {
        return userRepoInterface.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User " +username +" not found"));
    }

    @Override
    public boolean isUsernamePresent(String username){
        return userRepoInterface.findByUsername(username).isPresent();
    }

    @Override
    public void saveUser(User user) throws UsernameAlreadyPresent {
        if(this.isUsernamePresent(user.getUsername())){
            throw new UsernameAlreadyPresent(user.getUsername());
        } else {
            final String encodedPassword = Constants.USER_PASSWORD_ENCODER.encode(user.getPassword());
            User newUser = new User(user.getUsername(), encodedPassword, user.getUserRole());
            userRepoInterface.save(newUser);
        }
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepoInterface.findAll().stream().map(UserDTO::toUserDTO).toList();
    }

    @Override
    public void saveNewUser(String username, String password) throws UsernameAlreadyPresent {
        User newUser = new User(username, password, UserRole.ADMIN); // TODO user is added as ADMIN by default, can be later changed as per usecases
        userRepoInterface.save(newUser);
    }
}
