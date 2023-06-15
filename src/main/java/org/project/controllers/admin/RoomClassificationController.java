package org.project.controllers.admin;

import org.project.models.RoomClassification;
import org.project.service.RoomClassificationService;
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

@RequestMapping("/roomClassification")
@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class RoomClassificationController {

    private static final Logger logger = LoggerFactory.getLogger(RoomClassificationController.class);

    private RoomClassificationService roomClassificationService;

    @Autowired
    public RoomClassificationController(RoomClassificationService roomClassificationService) {
        this.roomClassificationService = roomClassificationService;
        logger.info("RoomClassificationController initialized with services.");
    }

    @GetMapping
    public String displayAllRoomClassifications(Model model) {
        List<RoomClassification> classifications = roomClassificationService.getAllRoomClassifications();
        model.addAttribute("roomClassifications", classifications);
        logger.info("Display all room classifications.");
        return "classifications";
    }

    @GetMapping("/create")
    public String initializeClassificationCreation(Model model) {
        model.addAttribute("roomClassification", new RoomClassification());
        logger.info("Initialize room classification creation.");
        return "create-classification";
    }

    @PostMapping("/create")
    public String createClassification(@Validated @ModelAttribute(name = "roomClassification") RoomClassification classification, BindingResult result) {
        if (result.hasErrors()) {
            logger.error("Error during room classification creation: {}", result.getAllErrors());
            return "create-classification";
        }
        roomClassificationService.saveRoomClassification(classification);
        logger.info("Room Classification created with id: {}", classification.getId());
        return "redirect:/roomClassification/" + classification.getId();
    }

    @GetMapping("/{id}")
    public String displayClassificationInfo(@PathVariable(name = "id") Long id, Model model) {
        RoomClassification classification = roomClassificationService.getRoomClassificationById(id);
        model.addAttribute("roomClassification", classification);
        logger.info("Display information for room classification id: {}", id);
        return "classification-info";
    }

    @GetMapping("/update/{id}")
    public String initializeClassificationUpdate(@PathVariable(name = "id") Long id, Model model) {
        RoomClassification classification = roomClassificationService.getRoomClassificationById(id);
        model.addAttribute("roomClassification", classification);
        logger.info("Initialize update for room classification id: {}", id);
        return "update-classification";
    }

    @PostMapping("/update/{id}")
    public String updateClassification(@PathVariable(name = "id") Long id, @ModelAttribute(name = "classification") RoomClassification classification, BindingResult result) {
        if (result.hasErrors()) {
            logger.error("Error during room classification update: {}", result.getAllErrors());
            return "update-classification";
        }
        roomClassificationService.updateRoomClassification(id, classification);
        logger.info("Room classification updated with id: {}", id);
        return "redirect:/roomClassification/" + classification.getId();
    }

    @GetMapping("/delete/{id}")
    public String deleteClassification(@PathVariable(name = "id") Long id) {
        roomClassificationService.deleteRoomClassification(id);
        logger.info("Room classification deleted with id: {}", id);
        return "redirect:/roomClassification";
    }
}
