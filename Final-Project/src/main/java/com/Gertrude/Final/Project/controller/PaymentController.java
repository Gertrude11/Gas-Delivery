package com.Gertrude.Final.Project.controller;

import com.Gertrude.Final.Project.model.CustomerOrder;
import com.Gertrude.Final.Project.model.EFuel;
import com.Gertrude.Final.Project.model.EPayment;
import com.Gertrude.Final.Project.model.Payment;
import com.Gertrude.Final.Project.services.CustomerOrderService;
import com.Gertrude.Final.Project.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
public class PaymentController {
    private PaymentService paymentService;
    private CustomerOrderService customerOrderService;

    @Autowired
    public PaymentController(PaymentService paymentService, CustomerOrderService customerOrderService) {
        this.paymentService = paymentService;
        this.customerOrderService = customerOrderService;
    }

    // home page with list of Payments
    @GetMapping("/payments")
    public String home(Model model) {
        List<Payment> allPayments = paymentService.getAllPayments();
        model.addAttribute("allPayments", allPayments);
        return "/payment/payment-list";
    }

    // create page: where we save new Payment
    @GetMapping("/payments/new")
    public String newPaymentForm(Model model) {
        Payment payment = new Payment();
        List<CustomerOrder> customerOrder = customerOrderService.getAllCustomerOrders();// get  all CustomerOrder combo box
        //CustomerOrder customerOrder = new CustomerOrder();
        List<EPayment> payment_mode = Arrays.stream(EPayment.values()).toList();
        model.addAttribute("customerOrders", customerOrder);
        model.addAttribute("payment", payment);
        model.addAttribute("payment_modes", payment_mode);

        return "/payment/new-payment";
    }
    // create Payment
    @PostMapping("/payments/new")
    public String newPayment(@ModelAttribute("payment") Payment payment, Model model) {
        paymentService.savePayment(payment,model); // save Payment
        return "redirect:/payments";
    }

    // update page: where we update Payment info
    @GetMapping("/payments/{id}/edit_payment")
    public String updatePaymentForm(@PathVariable String id, Model model) {
        UUID PaymentId = UUID.fromString(id);
        Optional<Payment> payment = paymentService.findByID(PaymentId);
        List<CustomerOrder> customerOrder = customerOrderService.getAllCustomerOrders();// get  all CustomerOrder combo box
        List<EPayment> payment_mode = Arrays.stream(EPayment.values()).toList();
        model.addAttribute("allCustomerOrder", customerOrder);
        model.addAttribute("payment_mode", payment_mode);
        model.addAttribute("paymentObj", payment);
        return "/payments/payment/edit-payment";
    }
    // update Payment
    @PostMapping("/{id}/edit_payment")
    public String editPayment(@PathVariable("id") String id, @ModelAttribute("paymentObj") Payment payment) {
        UUID PaymentId = UUID.fromString(id);
        payment.setId(PaymentId);
        paymentService.updatePayment(payment);
        return "redirect:/payments";
    }
    // delete Payment
    @GetMapping ("/{id}/delete_payment")
    public String removePayment(@PathVariable UUID id) {
        paymentService.deleteById(id);
        return "redirect:/payments";
    }

}
