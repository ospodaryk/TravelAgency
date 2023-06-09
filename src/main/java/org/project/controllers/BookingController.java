package org.project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BookingController {

    @RequestMapping("/")
    public String home(Model model) {
        return "home";
    }
}
