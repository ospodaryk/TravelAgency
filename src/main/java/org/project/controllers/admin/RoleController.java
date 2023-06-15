package org.project.controllers.admin;

import org.project.models.Role;
import org.project.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/role")
@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class RoleController {

    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);

    private RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
        logger.info("RoleController initialized with services.");
    }

    @GetMapping
    public String displayAllRoles(Model model) {
        List<Role> roles = roleService.getAllRoles();
        model.addAttribute("roles", roles);
        logger.info("Display all roles.");
        return "roles";
    }

    @GetMapping("/create")
    public String initializeRoleCreation(Model model) {
        model.addAttribute("role", new Role());
        logger.info("Initialize role creation.");
        return "create-role";
    }

    @PostMapping("/create")
    public String createRole(@Validated @ModelAttribute(name = "role") Role role, BindingResult result) {
        if (result.hasErrors()) {
            logger.error("Error during role creation: {}", result.getAllErrors());
            return "create-role";
        }
        roleService.saveRole(role);
        logger.info("Role created with id: {}", role.getId());
        return "redirect:/role/" + role.getId();
    }

    @GetMapping("/{id}")
    public String displayRoleInfo(@PathVariable(name = "id") Long id, Model model) {
        Role role = roleService.getRoleById(id);
        model.addAttribute("role", role);
        logger.info("Display information for role id: {}", id);
        return "role-info";
    }

    @GetMapping("/update/{id}")
    public String initializeRoleUpdate(@PathVariable(name = "id") Long id, Model model) {
        Role role = roleService.getRoleById(id);
        model.addAttribute("role", role);
        logger.info("Initialize update for role id: {}", id);
        return "update-role";
    }

    @PostMapping("/update/{id}")
    public String updateRole(@PathVariable(name = "id") Long id, @ModelAttribute(name = "role") Role role, BindingResult result) {
        if (result.hasErrors()) {
            logger.error("Error during role update: {}", result.getAllErrors());
            return "update-role";
        }
        roleService.updateRole(id, role);
        logger.info("Role updated with id: {}", id);
        return "redirect:/role";
    }

    @GetMapping("/delete/{id}")
    public String deleteRole(@PathVariable(name = "id") Long id) {
        roleService.deleteRole(id);
        logger.info("Role deleted with id: {}", id);
        return "redirect:/role";
    }
}
