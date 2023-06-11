package org.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.naming.AuthenticationException;

@Controller
public class HomeController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping({"/", "/home"})
    public String home(Model model) {
        return "home";
    }

    @GetMapping("/form-login")
    public String login() {
        return "login";
    }

    @PostMapping("/form-login")
    public String processLogin(@RequestParam("username") String username, @RequestParam("password") String password) {
        // Perform the login logic here, such as authenticating the user
        // You can use Spring Security or your custom authentication mechanism

        // Example using Spring Security
        try {
            // Authenticate the user with Spring Security's authentication manager
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

            // Set the authenticated user in the security context
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Redirect the user to the appropriate page based on the login result
            return "redirect:/home";  // Redirect to the home page if authentication is successful
        } catch (Exception ex) {
            // Authentication failed, redirect back to the login page with an error parameter
            return "redirect:/form-login?error=true";
        }
    }


}
