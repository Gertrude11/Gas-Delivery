package com.Gertrude.Final.Project.controller;


import com.Gertrude.Final.Project.model.User;
import com.Gertrude.Final.Project.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

      private FuelService fuelService;
      private CylinderService cylinderService;
      private PaymentService paymentService;
      private CustomerOrderService customerOrderService;
      private UserService userService;

    @Autowired
    public DashboardController(FuelService fuelService, CylinderService cylinderService, PaymentService paymentService, CustomerOrderService customerOrderService, UserService userService) {
        this.fuelService = fuelService;
        this.cylinderService = cylinderService;
        this.paymentService = paymentService;
        this.customerOrderService = customerOrderService;
        this.userService = userService;
    }









    @GetMapping("/dashboard")
        public String adminDashboard(@AuthenticationPrincipal UserDetails userDetails, Model model) {

        String email = userDetails.getUsername();

        // Fetch user-specific data using the email
        User userDto = userService.findUserByEmail(email);

        // Add the user-specific data to the model
        model.addAttribute("user", userDto);
            long fuelCount = fuelService.countFuels();
            long cylinderCount = cylinderService.countCylinders();
            long paymentCount = paymentService.countPayments();
            long orderCount = customerOrderService.countOrder();


            // Add calculated numbers to the model
            model.addAttribute("fuelCount", fuelCount);
            model.addAttribute("cylinderCount", cylinderCount);
             model.addAttribute("paymentCount", paymentCount);
        model.addAttribute("orderCount", orderCount);

            return "dashboard";
        }


}



