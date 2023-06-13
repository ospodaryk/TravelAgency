package org.project.controllers.admin;

import org.project.models.Country;
import org.project.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/country")
@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class CountryController {

    private CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @PreAuthorize("hasAuthority('STAFF')")
    @GetMapping
    public String showAllCountries(Model model) {
        List<Country> countries = countryService.getAllCountries();
        model.addAttribute("countries", countries);
        return "countries";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("country", new Country());
        return "create-country";
    }

    @PostMapping("/create")
    public String create(@Validated @ModelAttribute(name = "country") Country country, BindingResult result) {
        if (result.hasErrors()) {
            return "create-country";
        }
        countryService.saveCountry(country);
        return "redirect:/country/" + country.getCountryId();
    }

    @PreAuthorize("hasAuthority('STAFF')")
    @GetMapping("/{id}")
    public String read(@PathVariable(name = "id") Long id, Model model) {
        Country country = countryService.getCountryById(id);
        model.addAttribute("country", country);
        return "country-info";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable(name = "id") Long id, Model model) {
        Country country = countryService.getCountryById(id);
        model.addAttribute("country", country);
        return "update-country";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable(name = "id") Long id, @ModelAttribute(name = "country") Country country, BindingResult result) {
        if (result.hasErrors()) {
            return "update-country";
        }
        countryService.updateCountry(id, country);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") Long id) {
        countryService.deleteCountry(id);
        return "redirect:/";
    }

}
