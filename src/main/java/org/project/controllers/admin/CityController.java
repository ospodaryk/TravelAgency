package org.project.controllers.admin;

import org.project.models.City;
import org.project.service.CityService;
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

@RequestMapping("/city")
@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class CityController {

    private static final Logger logger = LoggerFactory.getLogger(CityController.class);

    private CityService cityService;
    private CountryService countryService;

    @Autowired
    public CityController(CityService cityService, CountryService countryService) {
        this.cityService = cityService;
        this.countryService = countryService;
        logger.info("CityController initialized with services.");
    }

    @PreAuthorize("hasAuthority('STAFF')")
    @GetMapping
    public String displayAllCities(Model model) {
        List<City> cities = cityService.getAllCities();
        model.addAttribute("cities", cities);
        logger.info("Display all cities.");
        return "cities";
    }

    @GetMapping("/create/{id}")
    public String initializeCityCreation(@PathVariable(name = "id") Long id, Model model) {
        model.addAttribute("city", new City());
        logger.info("Initialize city creation for country id: {}", id);
        return "create-city";
    }

    @PostMapping("/create/{id}")
    public String createCity(@PathVariable(name = "id") Long id, @Validated @ModelAttribute(name = "city") City city, BindingResult result) {
        if (result.hasErrors()) {
            logger.error("Error during city creation: {}", result.getAllErrors());
            return "create-city";
        }
        city.setCountry(countryService.getCountryById(id));
        cityService.saveCity(city);
        logger.info("City created with id: {}", city.getCityId());
        return "redirect:/city/" + city.getCityId();
    }

    @PreAuthorize("hasAuthority('STAFF')")
    @GetMapping("/{id}")
    public String displayCityInfo(@PathVariable(name = "id") Long id, Model model) {
        City city = cityService.getCityById(id);
        model.addAttribute("city", city);
        logger.info("Display information for city id: {}", id);
        return "city-info";
    }

    @GetMapping("/update/{id}")
    public String initializeCityUpdate(@PathVariable(name = "id") Long id, Model model) {
        City city = cityService.getCityById(id);
        model.addAttribute("city", city);
        model.addAttribute("allCountries", countryService.getAllCountries());
        logger.info("Initialize update for city id: {}", id);
        return "update-city";
    }

    @PostMapping("/update/{id}")
    public String updateCity(@PathVariable(name = "id") Long id, @ModelAttribute(name = "city") City city, BindingResult result) {
        if (result.hasErrors()) {
            logger.error("Error during city update: {}", result.getAllErrors());
            return "update-city";
        }
        cityService.updateCity(id, city);
        logger.info("City updated with id: {}", id);
        return "redirect:/city";
    }

    @GetMapping("/delete/{id}")
    public String deleteCity(@PathVariable(name = "id") Long id) {
        cityService.deleteCity(id);
        logger.info("City deleted with id: {}", id);
        return "redirect:/city";
    }
}
