package org.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

    @Autowired
    private AuthenticationManager authenticationManager;

    //    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/home")
    public String home(Model model) {
        return "home";
    }

    @PostMapping("/home")
    public String homePage(Model model) {
        // Your logic here
        return "redirect:/form-login?logout=true";
    }
}
