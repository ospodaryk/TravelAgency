package org.project.controllers.staff;

import org.project.configuration.security.Security;
import org.project.models.Role;
import org.project.models.User;
import org.project.service.RoleService;
import org.project.service.UserService;
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
//@PreAuthorize("hasAuthority('STAFF')")
public class UserController {

    private UserService userService;
    private RoleService roleService;

    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PreAuthorize("hasAuthority('STAFF')")
    @GetMapping
    public String showAllUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @PreAuthorize("hasAuthority('STAFF')")
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("user", new User());
        return "create-user";
    }

    @PostMapping("/create")
    public String create(@Validated @ModelAttribute(name = "user") User user, BindingResult result) {
        if (result.hasErrors()) {
            return "create-user";
        }
        user.setRole(roleService.getRoleById(2));
        userService.saveUser(user);
        return "redirect:/";
    }

    @GetMapping("/read")
    public String read( Model model, Principal principal) {
        Security userDetails = (Security) ((Authentication) principal).getPrincipal();
        long id=userDetails.getUserId();
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "user-more";
    }
    @PreAuthorize("#id==authentication.principal.getUserId()")
    @GetMapping("/{id}")
    public String readByID(@PathVariable(name = "id") long id, Model model, Principal principal) {
        System.out.println("\n\n"+id+"!="+principal.getName()+"\n\n");
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "user-more";
    }
    @GetMapping("/update")
    public String update( Model model, Principal principal) {
        Security userDetails = (Security) ((Authentication) principal).getPrincipal();
        long id=userDetails.getUserId();
        User user = userService.getUserById(id);
        System.out.println("BEFORE+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println(user);
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        model.addAttribute("user", user);
        model.addAttribute("allRoles", roleService.getAllRoles());
        Long role_id = user.getRole().getId();
        if (role_id == 1) {
            return "update-user-admin";
        }
        return "update-user";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Role.class, "role", new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                Role role = roleService.getRoleById(Long.parseLong(text));
                setValue(role);
            }
        });
    }

    @PostMapping("/update")
    public String update( @ModelAttribute(name = "user") User user, BindingResult result, Principal principal) {
        Security userDetails = (Security) ((Authentication) principal).getPrincipal();
        long id=userDetails.getUserId();
        if (result.hasErrors()) {
            System.out.println("\n\n\n\nERROR:");
            System.out.println(result.getAllErrors());
            System.out.println(user.getRole());
            System.out.println("\n\n\n\n");
            return "update-user";
        }
        System.out.println("ПРИЙШЛО З СИСТЕМИ +" + user);
        userService.updateUser(id, user);
        System.out.println("ОНОВЛЕНО" + user);
        return "redirect:/";
    }

    @GetMapping("/delete")
    public String delete( Principal principal) {
        Security userDetails = (Security) ((Authentication) principal).getPrincipal();
        long id=userDetails.getUserId();
        userService.deleteUser(id);
        return "redirect:/";
    }


}
