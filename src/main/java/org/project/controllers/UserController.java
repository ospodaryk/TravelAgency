package org.project.controllers;

import org.project.models.Hotel;
import org.project.models.User;
import org.project.service.RoleService;
import org.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping("/user")
@Controller
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping
    public String showAllUsers(Model model) {
        List<User> users = userService.getAllUsers();
        System.out.println("----------------------------------------------------------------");
        System.out.println(users);
        System.out.println("----------------------------------------------------------------");
        model.addAttribute("users", users);
        return "users";
    }
}
