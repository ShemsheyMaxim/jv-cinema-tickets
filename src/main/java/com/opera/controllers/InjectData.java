package com.opera.controllers;

import com.opera.model.Role;
import com.opera.model.RoleType;
import com.opera.model.User;
import com.opera.service.RoleService;
import com.opera.service.ShoppingCartService;
import com.opera.service.UserService;
import java.util.Set;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InjectData {
    private final UserService userService;
    private final RoleService roleService;
    private final ShoppingCartService shoppingCartService;

    @Autowired
    public InjectData(UserService userService, RoleService roleService,
                      ShoppingCartService shoppingCartService) {
        this.userService = userService;
        this.roleService = roleService;
        this.shoppingCartService = shoppingCartService;
    }

    @PostConstruct
    private void injectRolesAndUsers() {
        Role roleUser = new Role();
        roleUser.setTypeRole(RoleType.USER);
        roleService.add(roleUser);
        Role roleAdmin = new Role();
        roleAdmin.setTypeRole(RoleType.ADMIN);
        roleService.add(roleAdmin);
        User admin = new User();
        admin.setEmail("admin@gmail.com");
        admin.setPassword("Admin123");
        admin.setRoles(Set.of(roleAdmin));
        userService.add(admin);
        shoppingCartService.registerNewShoppingCart(admin);
    }
}
