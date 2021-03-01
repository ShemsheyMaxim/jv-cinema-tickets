package com.opera.service;

import com.opera.model.Role;

public interface RoleService {
    void add(Role role);

    Role getRoleByName(String roleName);
}
