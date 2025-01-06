package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ru.kata.spring.boot_security.demo.entity.model.Role;
import ru.kata.spring.boot_security.demo.entity.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.RoleServiceImpl;
import ru.kata.spring.boot_security.demo.service.UserService;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("admin/api")
public class AdminRestController {

    private final UserService userService;
    private final RoleService roleServiceImpl;
    private final UserServiceImpl userServiceImpl;


    @Autowired
    public AdminRestController(UserService userService, RoleService roleServiceImpl, UserServiceImpl userServiceImpl) {
        this.userService = userService;
        this.roleServiceImpl = roleServiceImpl;
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("/listOfUsers")
    public List<User> listOfUsers() {
        return userService.getListOfAllUsers();
    }

    @PostMapping("/save")
    public List<User> saveUser(@RequestBody User user) {
        Set<Role> roles = new HashSet<>();

        for (Role role : user.getRoles()) {
            roles.add(roleServiceImpl.getRoleByName(role.getRoleName()));
        }

        user.setRoles(roles);
        userService.addUser(user);
        return userService.getListOfAllUsers();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
    }

    @PutMapping("/update")
    public void updateUser(@RequestBody User user) {
        Set<Role> roles = new HashSet<>();

        for (Role role : user.getRoles()) {
            roles.add(roleServiceImpl.getRoleByName(role.getRoleName()));
        }

        user.setRoles(roles);
        userService.updateUser(user);
    }

}
