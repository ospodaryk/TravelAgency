package org.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/home")
    public String home(Model model) {
        return "home";
    }

    @PostMapping("/home")
    public String homePage(Model model) {
        return "redirect:/form-login?logout=true";
    }
}
