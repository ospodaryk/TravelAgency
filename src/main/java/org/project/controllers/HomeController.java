package org.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    private final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/home")
    public String home(Model model) {
        logger.info("Entering home");
        logger.info("Exiting home");
        return "home";
    }

    @PostMapping("/home")
    public String homePage(Model model) {
        logger.info("Entering homePage");
        logger.info("Exiting homePage");
        return "redirect:/form-login?logout=true";
    }
}
