package org.project.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.project.models.Country;
import org.project.models.Hotel;
import org.project.models.Role;
import org.project.models.City;
import org.project.service.CityService;
import org.project.service.CountryService;
import org.project.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.beans.PropertyEditorSupport;
import java.util.List;

@Controller
public class HomeController {
    @PreAuthorize("hasAuthority('USER')")
    @RequestMapping("/")
    public String home(Model model) {
        return "home";
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

}
