package org.project.controllers.admin;

import org.project.models.Country;
import org.project.service.CountryService;
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

@RequestMapping("/country")
@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class CountryController {

    private static final Logger logger = LoggerFactory.getLogger(CountryController.class);

    private CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
        logger.info("CountryController initialized with services.");
    }

    @PreAuthorize("hasAuthority('STAFF')")
    @GetMapping
    public String displayAllCountries(Model model) {
        List<Country> countries = countryService.getAllCountries();
        model.addAttribute("countries", countries);
        logger.info("Display all countries.");
        return "countries";
    }

    @GetMapping("/create")
    public String initializeCountryCreation(Model model) {
        model.addAttribute("country", new Country());
        logger.info("Initialize country creation.");
        return "create-country";
    }

    @PostMapping("/create")
    public String createCountry(@Validated @ModelAttribute(name = "country") Country country, BindingResult result) {
        if (result.hasErrors()) {
            logger.error("Error during country creation: {}", result.getAllErrors());
            return "create-country";
        }
        countryService.saveCountry(country);
        logger.info("Country created with id: {}", country.getCountryId());
        return "redirect:/country/" + country.getCountryId();
    }

    @PreAuthorize("hasAuthority('STAFF')")
    @GetMapping("/{id}")
    public String displayCountryInfo(@PathVariable(name = "id") Long id, Model model) {
        Country country = countryService.getCountryById(id);
        model.addAttribute("country", country);
        logger.info("Display information for country id: {}", id);
        return "country-info";
    }

    @GetMapping("/update/{id}")
    public String initializeCountryUpdate(@PathVariable(name = "id") Long id, Model model) {
        Country country = countryService.getCountryById(id);
        model.addAttribute("country", country);
        logger.info("Initialize update for country id: {}", id);
        return "update-country";
    }

    @PostMapping("/update/{id}")
    public String updateCountry(@PathVariable(name = "id") Long id, @ModelAttribute(name = "country") Country country, BindingResult result) {
        if (result.hasErrors()) {
            logger.error("Error during country update: {}", result.getAllErrors());
            return "update-country";
        }
        countryService.updateCountry(id, country);
        logger.info("Country updated with id: {}", id);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteCountry(@PathVariable(name = "id") Long id) {
        countryService.deleteCountry(id);
        logger.info("Country deleted with id: {}", id);
        return "redirect:/";
    }

}
