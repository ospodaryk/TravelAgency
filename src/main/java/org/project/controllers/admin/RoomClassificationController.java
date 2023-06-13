package org.project.controllers.admin;

import org.project.models.RoomClassification;
import org.project.service.RoomClassificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/roomClassification")
@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class RoomClassificationController {

    private RoomClassificationService roomClassificationService;

    @Autowired
    public RoomClassificationController(RoomClassificationService roomClassificationService) {
        this.roomClassificationService = roomClassificationService;
    }

    @GetMapping
    public String showAllRoomClassifications(Model model) {
        List<RoomClassification> classifications = roomClassificationService.getAllRoomClassifications();
        model.addAttribute("roomClassifications", classifications);
        return "classifications";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("roomClassification", new RoomClassification());
        return "create-classification";
    }

    @PostMapping("/create")
    public String create(@Validated @ModelAttribute(name = "roomClassification") RoomClassification classification, BindingResult result) {
        if (result.hasErrors()) {
            return "create-classification";
        }
        roomClassificationService.saveRoomClassification(classification);
        return "redirect:/roomClassification/" + classification.getId();
    }

    @GetMapping("/{id}")
    public String read(@PathVariable(name = "id") Long id, Model model) {
        RoomClassification classification = roomClassificationService.getRoomClassificationById(id);
        model.addAttribute("roomClassification", classification);
        return "classification-info";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable(name = "id") Long id, Model model) {
        RoomClassification classification = roomClassificationService.getRoomClassificationById(id);
        model.addAttribute("roomClassification", classification);
        return "update-classification";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable(name = "id") Long id, @ModelAttribute(name = "classification") RoomClassification classification, BindingResult result) {
        if (result.hasErrors()) {
            return "update-classification";
        }
        roomClassificationService.updateRoomClassification(id, classification);
        return "redirect:/roomClassification/" + classification.getId();
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") Long id) {
        roomClassificationService.deleteRoomClassification(id);
        return "redirect:/roomClassification";
    }
}
