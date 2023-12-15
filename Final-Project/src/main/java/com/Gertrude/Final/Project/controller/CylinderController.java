package com.Gertrude.Final.Project.controller;

import com.Gertrude.Final.Project.model.Cylinder;
import com.Gertrude.Final.Project.model.Fuel;
import com.Gertrude.Final.Project.services.CylinderService;
import com.Gertrude.Final.Project.services.FuelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
public class CylinderController {
    private final CylinderService cylinderService;
    private  final FuelService fuelService;

    @Autowired
    public CylinderController(CylinderService cylinderService, FuelService fuelService) {
        this.cylinderService = cylinderService;
        this.fuelService = fuelService;
    }

    // home page with list of Cylinders
    @GetMapping("/cylinders")
    public String home(Model model) {
        List<Cylinder> allCylinders = cylinderService.getAllCylinders();
        model.addAttribute("allCylinders", allCylinders);
        return "/cylinder/cylinder-list";
    }

    // create page: where we save new Cylinder
    @GetMapping("/cylinders/new")
    public String newCylinderForm(Model model) {
        Cylinder cylinder = new Cylinder();
        List<Fuel> fuel = fuelService.getAllFuels();// get  all fuel combo box
        model.addAttribute("cylinder", cylinder);
        model.addAttribute("fuel", fuel);
        return "/cylinder/new-cylinder";
    }
    // create Cylinder
    @PostMapping("/cylinders/new")
    public String newCylinder(@ModelAttribute("cylinder") Cylinder cylinder) {
        cylinderService.saveCylinder(cylinder); // save Cylinder
        return "redirect:/cylinders";
    }

    // update page: where we update Cylinder info
    @GetMapping("/cylinders/{id}/edit_cylinder")
    public String updateCylinderForm(@PathVariable String id, Model model) {
        UUID cylinderId = UUID.fromString(id);
        Optional<Cylinder> cylinder = cylinderService.findByID(cylinderId);
        List<Fuel> fuel = fuelService.getAllFuels();// get  all fuel combo box
        model.addAttribute("cylinderObj", cylinder);
        model.addAttribute("fuel", fuel);
        return "/cylinder/edit-cylinder";
    }
    // update Cylinder
    @PostMapping("/cylinders/{id}/edit_cylinder")
    public String editCylinder(@PathVariable String id, @ModelAttribute("cylinderObj") Cylinder cylinder) {
        UUID cylinderId = UUID.fromString(id);
        cylinder.setId(cylinderId);
        cylinderService.updateCylinder(cylinder);
        return "redirect:/cylinders";
    }
    // delete Cylinder
    @GetMapping ("/cylinders/{id}/delete_cylinder")
    public String removeCylinder(@PathVariable UUID id) {

        cylinderService.deleteById(id);
        return "redirect:/cylinders";
    }
}



