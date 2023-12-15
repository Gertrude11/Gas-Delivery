package com.Gertrude.Final.Project.controller;

import com.Gertrude.Final.Project.model.Cylinder;
import com.Gertrude.Final.Project.model.EFuel;
import com.Gertrude.Final.Project.model.Fuel;
import com.Gertrude.Final.Project.services.CylinderService;
import com.Gertrude.Final.Project.services.FuelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
public class FuelController {


    private FuelService fuelService;
  //  private CylinderService cylinderService;

    @Autowired
    public FuelController(FuelService fuelService) {
        this.fuelService = fuelService;
    }

    // home page with list of Fuels
    @GetMapping("/fuels")
    public String home(Model model) {
        List<Fuel> allFuels = fuelService.getAllFuels();
        model.addAttribute("allFuels", allFuels);
        return "/fuel/fuel-list";
    }

    // create page: where we save new Fuel
    @GetMapping("/fuels/new")
    public String newFuelForm(Model model) {
        Fuel Fuel = new Fuel();
      //  List<Cylinder> cylinder = cylinderService.getAllCylinders();// get  all cylinder combo box
        List<EFuel> fuel_type = Arrays.stream(EFuel.values()).toList(); // get all book categories to be populated in category combo box
       // model.addAttribute("allCylinder", cylinder);
        model.addAttribute("fuel", Fuel);
        model.addAttribute("types", fuel_type);
        return "/fuel/new-fuel";
    }
    // create Fuel
    @PostMapping("/fuels/new")
    public String newFuel(@ModelAttribute("fuel") Fuel fuel) {
        fuelService.saveFuel(fuel); // save Fuel
        return "redirect:/fuels";
    }

    // update page: where we update Fuel info
    @GetMapping("/fuels/{id}/edit_fuel")
    public String updateFuelForm(@PathVariable("id") String id, Model model) {
        UUID FuelId = UUID.fromString(id);
        List<EFuel> fuel_type = Arrays.stream(EFuel.values()).toList();
        Optional<Fuel> Fuel = fuelService.findByID(FuelId);
        model.addAttribute("fuelObj", Fuel);
        model.addAttribute("types", fuel_type);
        return "fuel/edit-fuel";
    }
    // update Fuel
    @PostMapping("/fuels/{id}/edit_fuel")
    public String editFuel(@PathVariable("id") String id, @ModelAttribute("fuelObj") Fuel Fuel) {
        UUID FuelId = UUID.fromString(id);
        Fuel.setId(FuelId);
        fuelService.updateFuel(Fuel);
        return "redirect:/fuels";
    }
    // delete Fuel
    @GetMapping ("/fuels/{id}/delete_fuel")
    public String removeFuel(@PathVariable UUID id) {
        fuelService.deleteById(id);
        return "redirect:/fuels";
    }
}
