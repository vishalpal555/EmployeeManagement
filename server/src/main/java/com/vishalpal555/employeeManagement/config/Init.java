package com.vishalpal555.employeeManagement.config;

import com.vishalpal555.employeeManagement.CustomException.UsernameAlreadyPresent;
import com.vishalpal555.employeeManagement.pojo.User;
import com.vishalpal555.employeeManagement.pojo.UserRole;
import com.vishalpal555.employeeManagement.service.implementation.UserServiceImpl;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Init {
    private static final Logger LOGGER = LoggerFactory.getLogger(Init.class);
    @Autowired
    UserServiceImpl userService;

    @PostConstruct
    public void init() {
        LOGGER.info("initialize application");
        try{
            this.setSuperUserIfAbsent();
        } catch (Exception e){
            LOGGER.error("handled general exception", e);
        }
    }

    private void setSuperUserIfAbsent(){
        final String superUsername = "vishalpal555@superuser.com";
        try {
            User superUser = new User(superUsername,
                        "12345678",
                        UserRole.ADMIN
            );
            userService.saveUser(superUser);
        } catch (UsernameAlreadyPresent usernameAlreadyPresent){
            LOGGER.info("Super user already present in the database");
        } catch (Exception e){
            LOGGER.error("handled general exception", e);
        }
    }
}
