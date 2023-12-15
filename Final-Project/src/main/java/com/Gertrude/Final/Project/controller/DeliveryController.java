package com.Gertrude.Final.Project.controller;

import com.Gertrude.Final.Project.model.Delivery;
import com.Gertrude.Final.Project.model.Payment;
import com.Gertrude.Final.Project.services.DeliveryService;
import com.Gertrude.Final.Project.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

//@Controller
public class DeliveryController {
//    private final DeliveryService deliveryService;
//    private  final PaymentService paymentService;
//
//    @Autowired
//    public DeliveryController(DeliveryService deliveryService, PaymentService paymentService) {
//        this.deliveryService = deliveryService;
//        this.paymentService = paymentService;
//    }
//
//    // home page with list of Deliverys
//    @GetMapping("/deliveries")
//    public String home(Model model) {
//        List<Delivery> allDeliverys = deliveryService.getAllDeliveries();
//        model.addAttribute("allDeliverys", allDeliverys);
//        return "/delivery/delivery-list";
//    }
//
//    // create page: where we save new Delivery
//    @GetMapping("/deliveries/new")
//    public String newDeliveryForm(Model model) {
//        Delivery delivery = new Delivery();
//        List<Payment> payment = paymentService.getAllPayments();// get  all Payment combo box
//        model.addAttribute("delivery", delivery);
//        model.addAttribute("payment", payment);
//        return "/delivery/new-delivery";
//    }
//    // create Delivery
//    @PostMapping("/deliveries/new")
//    public String newDelivery(@ModelAttribute("Delivery") Delivery Delivery) {
//        deliveryService.saveDelivery(Delivery); // save Delivery
//        return "redirect:/deliveries";
//    }
//
//    // update page: where we update Delivery info
//    @GetMapping("/{id}/edit_delivery")
//    public String updateDeliveryForm(@PathVariable String id, Model model) {
//        UUID DeliveryId = UUID.fromString(id);
//        Optional<Delivery> Delivery = deliveryService.findByID(DeliveryId);
//        List<Payment> Payment = paymentService.getAllPayments();// get  all Payment combo box
//        model.addAttribute("deliveryObj", Delivery);
//        model.addAttribute("Payment", Payment);
//        return "/Delivery/edit-Delivery";
//    }
//    // update Delivery
//    @PostMapping("/{id}/edit_delivery")
//    public String editDelivery(@PathVariable String id, @ModelAttribute("deliveryObj") Delivery Delivery) {
//        UUID DeliveryId = UUID.fromString(id);
//        Delivery.setId(DeliveryId);
//        deliveryService.updateDelivery(Delivery);
//        return "redirect:/deliveries";
//    }
//    // delete Delivery
//    @GetMapping ("/{id}/delete_delivery")
//    public String removeDelivery(@PathVariable UUID id) {
//        deliveryService.deleteById(id);
//        return "redirect:/deliveries";
//    }
}
