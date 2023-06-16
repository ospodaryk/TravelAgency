package org.project.controllers.staff;

import org.project.configuration.security.Security;
import org.project.models.Role;
import org.project.models.User;
import org.project.service.RoleService;
import org.project.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.beans.PropertyEditorSupport;
import java.security.Principal;
import java.util.List;

@RequestMapping("/user")
@Controller
public class UserController {

    private UserService userService;
    private RoleService roleService;

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
        logger.info("UserController initialized");
    }

    @PreAuthorize("hasAuthority('STAFF') or hasAuthority('ADMIN')")
    @GetMapping
    public String showAllUsers(Model model) {
        logger.info("Entering showAllUsers");
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        logger.info("Exiting showAllUsers");
        return "users";
    }

    @PreAuthorize("hasAuthority('STAFF') or hasAuthority('ADMIN')")
    @GetMapping("/create")
    public String create(Model model) {
        logger.info("Entering create");
        model.addAttribute("user", new User());
        logger.info("Exiting create");
        return "create-user";
    }

    @PostMapping("/create")
    public String create(@Validated @ModelAttribute(name = "user") User user, BindingResult result) {
        logger.info("Entering create with POST");
        if (result.hasErrors()) {
            logger.warn("Form validation errors occurred");
            return "create-user";
        }
        user.setRole(roleService.getRoleById(2));
        userService.saveUser(user);
        logger.info("Exiting create with POST");
        return "redirect:/";
    }

    @GetMapping("/read")
    public String read(Model model, Principal principal) {
        logger.info("Entering read");
        Security userDetails = (Security) ((Authentication) principal).getPrincipal();
        long id = userDetails.getUserId();
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        logger.info("Exiting read");
        return "user-more";
    }

    @PreAuthorize("hasAuthority('STAFF') or hasAuthority('ADMIN')")
    @GetMapping("/update/{id}")
    public String updateADMIN(@PathVariable(name = "id") Integer id, Model model) {
        logger.info("Entering updateADMIN");
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("allRoles", roleService.getAllRoles());
        Long role_id = user.getRole().getId();
        logger.info("Exiting updateADMIN");
        return "admin-update-user";
    }

    @PreAuthorize("hasAuthority('STAFF') or hasAuthority('ADMIN')")
    @PostMapping("/update/{id}")
    public String updateADMIN(Model model, @PathVariable(name = "id") Long id, @ModelAttribute(name = "user") User user, BindingResult result) {
        logger.info("Entering updateADMIN with POST");
        if (result.hasErrors()) {
            logger.warn("Form validation errors occurred");
            model.addAttribute("code", result.getAllErrors().get(0).getCode());
            model.addAttribute("message", result.getAllErrors());
            return "error";
        }
        userService.updateUser(id, user);
        logger.info("Exiting updateADMIN with POST");
        return "redirect:/user";
    }

    @PreAuthorize("hasAuthority('STAFF') or hasAuthority('ADMIN')")
    @GetMapping("/delete/{id}")
    public String deleteADMIN(@PathVariable(name = "id") Integer id) {
        logger.info("Entering deleteADMIN");
        userService.deleteUser(id);
        logger.info("Exiting deleteADMIN");
        return "redirect:/user";
    }

    @PreAuthorize("hasAuthority('STAFF') or hasAuthority('ADMIN')")
    @GetMapping("/read/{id}")
    public String readByID(@PathVariable(name = "id") long id, Model model, Principal principal) {
        logger.info("Entering readByID");
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        logger.info("Exiting readByID");
        return "user-info";
    }

    @GetMapping("/update")
    public String update(Model model, Principal principal) {
        logger.info("Entering update");
        Security userDetails = (Security) ((Authentication) principal).getPrincipal();
        long id = userDetails.getUserId();
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("allRoles", roleService.getAllRoles());
        Long role_id = user.getRole().getId();
        logger.info("Exiting update");
        if (role_id == 1) {
            return "admin-update-user";
        }
        return "update-user";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        logger.info("Entering initBinder");
        binder.registerCustomEditor(Role.class, "role", new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                Role role = roleService.getRoleById(Long.parseLong(text));
                setValue(role);
            }
        });
        logger.info("Exiting initBinder");
    }

    @PostMapping("/update")
    public String update(Model model, @ModelAttribute(name = "user") User user, BindingResult result, Principal principal) {
        logger.info("Entering update with POST");
        Security userDetails = (Security) ((Authentication) principal).getPrincipal();
        long id = userDetails.getUserId();
        if (result.hasErrors()) {
            logger.warn("Form validation errors occurred");
            model.addAttribute("code", result.getAllErrors().get(0).getCode());
            model.addAttribute("message", result.getAllErrors());
            return "error";
        }
        userService.updateUser(id, user);
        logger.info("Exiting update with POST");
        return "redirect:/user";
    }

    @GetMapping("/delete")
    public String delete(Principal principal) {
        logger.info("Entering delete");
        Security userDetails = (Security) ((Authentication) principal).getPrincipal();
        long id = userDetails.getUserId();
        userService.deleteUser(id);
        logger.info("Exiting delete");
        return "redirect:/user";
    }

}
