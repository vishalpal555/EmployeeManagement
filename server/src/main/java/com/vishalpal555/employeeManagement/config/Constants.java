package com.vishalpal555.employeeManagement.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Constants {
    public static final String TOKEN_SIGNING_KEY = "4SEb3vqwIF1AqJwnRPL9is2CUqg6follZE2QLJGYeSUXZNTSffDvMmpynmKtEb0PwosFCqNl3mBqDtZjycN3bQ==";
    public static final PasswordEncoder USER_PASSWORD_ENCODER = new BCryptPasswordEncoder();
    public static final String EMAIL_PATTERN_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
}
