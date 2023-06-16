package org.project.controllers;

import org.project.controllers.staff.UserController;
import org.project.models.User;
import org.project.service.RoleService;
import org.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SecurityController {

    private final Logger logger = LoggerFactory.getLogger(SecurityController.class);
    private UserService userService;
    private RoleService roleService;


    @Autowired
    public SecurityController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
        logger.info("SecurityController initialized");
    }
    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping({"/", "/form-login"})
    public String login(Model model, @RequestParam(value = "error", required = false) String error) {
        logger.info("Entering login");
        if (error != null) {
            model.addAttribute("error", true);
        }
        logger.info("Exiting login");
        return "login";
    }

    @PostMapping("/form-login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, Model model) {
        logger.info("Entering login with POST");
        try {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
            Authentication auth = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(auth);
            logger.info("Exiting login with POST - successful login");
            return "redirect:/home";
        } catch (AuthenticationException e) {
            model.addAttribute("error", true);
            logger.warn("Exiting login with POST - failed login");
            return "redirect:/form-login?error=true";
        }
    }

    @GetMapping("/create")
    public String create(Model model) {
        logger.info("Entering create");
        model.addAttribute("user", new User());
        logger.info("Exiting create");
        return "login-create-user";
    }
    @PostMapping("/create")
    public String create(@Validated @ModelAttribute(name = "user") User user, BindingResult result) {
        logger.info("Entering create with POST");
        if (result.hasErrors()) {
            logger.warn("Form validation errors occurred");
            return "login-create-user";
        }
        user.setRole(roleService.getRoleById(2));
        userService.saveUser(user);
        logger.info("Exiting create with POST");
        return "redirect:/";
    }
}
