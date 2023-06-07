package org.project.controllers;

import org.project.models.Country;
import org.project.models.City;
import org.project.service.CountryService;
import org.project.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/city")
@Controller
public class CityController {

    private CityService cityService;
    private CountryService countryService;

    @Autowired
    public CityController(CityService cityService, CountryService countryService) {
        this.cityService = cityService;
        this.countryService = countryService;
    }

    @GetMapping
    public String showAllCities(Model model) {
        List<City> cities = cityService.getAllCities();
        model.addAttribute("cities", cities);
        return "cities";
    }

    @GetMapping("/create/{id}")
    public String create(@PathVariable(name = "id") Long id,Model model) {
        model.addAttribute("city", new City());
        return "create-city";
    }

    @PostMapping("/create/{id}")
    public String create(@PathVariable(name = "id") Long id,@Validated @ModelAttribute(name = "city") City city, BindingResult result) {
        if (result.hasErrors()) {
            return "create-city";
        }
        city.setCountry(countryService.getCountryById(id));
        cityService.saveCity(city);
        return "redirect:/city/" + city.getCityId();
    }

    @GetMapping("/{id}")
    public String read(@PathVariable(name = "id") Long id, Model model) {
        City city = cityService.getCityById(id);
        model.addAttribute("city", city);
        return "city-info";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable(name = "id") Long id, Model model) {
        City city = cityService.getCityById(id);
        model.addAttribute("city", city);
        model.addAttribute("allCountries", countryService.getAllCountries());
        return "update-city";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable(name = "id") Long id, @ModelAttribute(name = "city") City city, BindingResult result) {
        if (result.hasErrors()) {
            return "update-city";
        }
        cityService.updateCity(id, city);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") Long id) {
        cityService.deleteCity(id);
        return "redirect:/";
    }
}
