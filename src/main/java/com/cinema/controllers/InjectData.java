package com.cinema.controllers;

import com.cinema.model.Role;
import com.cinema.model.RoleType;
import com.cinema.model.User;
import com.cinema.service.RoleService;
import com.cinema.service.ShoppingCartService;
import com.cinema.service.UserService;
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
