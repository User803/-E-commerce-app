package com.droid.E_commerce.app.controller;

import com.droid.E_commerce.app.entity.User;
import com.droid.E_commerce.app.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/add/user")
    public String addUser() {
        return "AddUser";
    }

    @PostMapping("/add/user")
    public String addUser(User user) {
        userService.createUser(user);

        return "Login";
    }

    @GetMapping("/update/user/{id}")
    public String updateUser(@PathVariable Long id, Model model) {
        model.addAttribute("admin", userService.getUserById(id));

        return "UpdateUser";
    }

    @PostMapping("/update/user")
    public String updateUser(User user) {
        userService.createUser(user);

        return "/admin/home";
    }

    @DeleteMapping("/delete/user/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);

        return "/admin/home";
    }

}
