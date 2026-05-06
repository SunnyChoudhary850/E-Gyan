package com.egyan.controller;

//// to test the ui http://localhost:8080/swagger-ui/index.html

import com.egyan.entity.Course;
import com.egyan.entity.User;
import com.egyan.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }
    @PostMapping("/login")
    public User login(@RequestBody User user) {
        String email = user.getEmail();
        String password = user.getPassword();
        return userService.login(email, password);
    }
}