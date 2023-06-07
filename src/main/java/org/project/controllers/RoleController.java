package org.project.controllers;

import org.project.models.Role;
import org.project.models.User;
import org.project.service.RoleService;
import org.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.beans.PropertyEditorSupport;
import java.util.List;

@RequestMapping("/role")
@Controller
public class RoleController {

    private RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public String showAllRoles(Model model) {
        List<Role> roles = roleService.getAllRoles();
        model.addAttribute("roles", roles);
        return "roles";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("role", new Role());
        return "create-role";
    }

    @PostMapping("/create")
    public String create(@Validated @ModelAttribute(name = "role") Role role, BindingResult result) {
        if (result.hasErrors()) {
            return "create-role";
        }
        roleService.saveRole(role);
        return "redirect:/role/" + role.getId();
    }

    @GetMapping("/{id}")
    public String read(@PathVariable(name = "id") Long id, Model model) {
        Role role = roleService.getRoleById(id);
        model.addAttribute("role", role);
        return "role-info";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable(name = "id") Long id, Model model) {
        Role role = roleService.getRoleById(id);
        model.addAttribute("role", role);
        return "update-role";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable(name = "id") Long id, @ModelAttribute(name = "role") Role role, BindingResult result) {
        if (result.hasErrors()) {
            return "update-role";
        }
        roleService.updateRole(id, role);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") Long id) {
        roleService.deleteRole(id);
        return "redirect:/";
    }
}
