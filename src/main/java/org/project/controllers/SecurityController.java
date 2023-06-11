package org.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
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
public class SecurityController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping({"/", "/form-login"})
    public String login(Model model, @RequestParam(value = "error", required = false) String error) {
        if (error != null) {
            model.addAttribute("error", true);
        }
        return "login";
    }
    @PostMapping("/form-login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, Model model) {
        try {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
            Authentication auth = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(auth);
            return "redirect:/home"; // Redirect to home page after successful login
        } catch (AuthenticationException e) {
            model.addAttribute("error", true);
            return "redirect:/form-login?error=true";
        }
    }
    @PostMapping("/logout")
    public String logout(HttpServletRequest request) throws ServletException {
        // Custom logic here...
        request.logout();
        return "redirect:/form-login?logout=true";
    }
}
