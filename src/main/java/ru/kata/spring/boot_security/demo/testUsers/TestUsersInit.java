package ru.kata.spring.boot_security.demo.testUsers;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.entity.model.Role;
import ru.kata.spring.boot_security.demo.entity.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;

import ru.kata.spring.boot_security.demo.service.UserService;

@Component
@Transactional
public class TestUsersInit {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public TestUsersInit(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @PostConstruct
    private void init() {

        User admin = new User("admin", "admin");
        User user = new User("user", "user");

        if (roleService.getRoleByName("ROLE_ADMIN") == null) {
            roleService.saveRole(new Role("ROLE_ADMIN"));
            admin.addRole(roleService.getRoleByName("ROLE_ADMIN"));
        } else {
            admin.addRole(roleService.getRoleByName("ROLE_ADMIN"));
        }

        if (roleService.getRoleByName("ROLE_USER") == null) {
            roleService.saveRole(new Role("ROLE_USER"));
            user.addRole(roleService.getRoleByName("ROLE_USER"));
        } else {
            user.addRole(roleService.getRoleByName("ROLE_USER"));
        }
        admin.setEmail("admin@admin.com");
        admin.setFirstName("admin");
        admin.setLastName("admin");
        admin.setAge(11);
        userService.addUser(admin);
        userService.addUser(user);
    }

    @PreDestroy
    private void destroy() {
        userService.deleteTestUsers();
    }

}
