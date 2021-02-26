package com.opera.service;

import com.opera.model.User;

public interface AuthenticationService {
    User register(String email, String password);
}
