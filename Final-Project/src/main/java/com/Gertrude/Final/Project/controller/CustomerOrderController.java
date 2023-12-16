package com.Gertrude.Final.Project.controller;

import com.Gertrude.Final.Project.email.EmailService;
import com.Gertrude.Final.Project.model.*;
import com.Gertrude.Final.Project.services.CustomerOrderService;
import com.Gertrude.Final.Project.services.CylinderService;
import com.Gertrude.Final.Project.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
public class CustomerOrderController {
    private CustomerOrderService customerOrderService;
    private PaymentService paymentService;
    private CylinderService CylinderService;

    private EmailService emailService;

    @Autowired
    public CustomerOrderController(CustomerOrderService customerOrderService, PaymentService paymentService, com.Gertrude.Final.Project.services.CylinderService cylinderService, EmailService emailService) {
        this.customerOrderService = customerOrderService;
        this.paymentService = paymentService;
        CylinderService = cylinderService;
        this.emailService = emailService;
    }








    // home page with list of CustomerOrders
    @GetMapping("/customerOrders")
    public String home(Model model) {
        List<CustomerOrder> allCustomerOrders = customerOrderService.getAllCustomerOrders();
        model.addAttribute("allCustomerOrders", allCustomerOrders);
        return "/order/order-list";
    }

    // create page: where we save new CustomerOrder
    @GetMapping("/customerOrders/new")
    public String newCustomerOrderForm(Model model) {
        CustomerOrder customerOrder = new CustomerOrder();
      //  List<Customer> customer = customerService.getAllCustomers();// get  all Customer combo box
        List<Cylinder> cylinder = CylinderService.getAllCylinders();
        //List<Stock> stock = customerService.getAllCustomers();
//Stock stock = new Stock();

   //     model.addAttribute("allCustomer", customer);
        model.addAttribute("allCylinders", cylinder);
        model.addAttribute("customerOrder", customerOrder);
//        if(customerOrder.getQuantity()>stock.getQuantity()){
//            return"Quantity not available";
//        }
//        else{
//            return "/customerOrder/new-customerOrder";
//        }
        return "/order/new-order";

    }
    // create CustomerOrder

//    public String newCustomerOrder(@ModelAttribute("customerOrder") CustomerOrder customerOrder) {
//        customerOrderService.saveCustomerOrder(customerOrder); // save CustomerOrder
//        return "redirect:/customerOrders";
//    }
@PostMapping("/customerOrders/new")
    public String newCustomerOrder(@ModelAttribute("customerOrder") CustomerOrder customerOrder, Model model) {
        try {
            customerOrderService.saveCustomerOrder(customerOrder, model);
            //return "redirect:/orders/success"; // Redirect to a success page
           return  "redirect:/customerOrders?success";
        } catch (InsufficientQuantityException e) {

            model.addAttribute("errorMessage", e.getMessage());
            return "/order/new-order"; // Redirect to an error page
        }


    }

    // update page: where we update CustomerOrder info
    @GetMapping("{id}/edit_customerOrder")
    public String updateCustomerOrderForm(@PathVariable String id, Model model) {
        UUID customerOrderId = UUID.fromString(id);
        Optional<CustomerOrder> customerOrder = customerOrderService.findByID(customerOrderId);
        model.addAttribute("customerOrderObj", customerOrder);
        return "/customerOrders/customerOrder/edit-customerOrder";
    }
    // update CustomerOrder
    @PostMapping("/{id}/edit_customerOrder")
    public String editCustomerOrder(@PathVariable("id") String id, @ModelAttribute("customerOrderObj") CustomerOrder customerOrder) {
        UUID customerOrderId = UUID.fromString(id);
        customerOrder.setId(customerOrderId);
        customerOrderService.updateCustomerOrder(customerOrder);
        return "redirect:/customerOrders";
    }
    // delete CustomerOrder
    @GetMapping ("/{id}/delete_customerOrder")
    public String removeCustomerOrder(@PathVariable UUID id) {
        customerOrderService.deleteById(id);
        return "redirect:/customerOrders";
    }

    @GetMapping("{orderId}/makepayment")
    public String showPaymentForm(@PathVariable String orderId, Model model) {
        // Provide necessary data for the payment form
        UUID customerOrderId = UUID.fromString(orderId);
        Optional<CustomerOrder>  customerOrders= customerOrderService.findByID(customerOrderId);
        List<EPayment> payment_mode = Arrays.stream(EPayment.values()).toList();
        model.addAttribute("payment_modes", payment_mode);

        model.addAttribute("allCustomerOrders", customerOrders);
        model.addAttribute("payment", new Payment());
        return "payment/new-payment";
    }

///

    @PostMapping("/{orderId}/makepayment")
    public String makePayment(@PathVariable UUID orderId, @ModelAttribute Payment payment, Model model) {
        // Retrieve the existing CustomerOrder using the orderId
        Optional<CustomerOrder> optionalCustomerOrder = customerOrderService.findByID(orderId);

        // Use orElseThrow to get the CustomerOrder or throw an exception if not found
        CustomerOrder customerOrder = optionalCustomerOrder.orElseThrow(() -> new RuntimeException("CustomerOrder not found"));

        // Set necessary fields for the payment and update CustomerOrder status
        payment.setCustomerOrder(customerOrder);
        paymentService.savePayment(payment,model);

        // Update the status of the CustomerOrder to "paid" (adjust accordingly based on your code)
       // customerOrder.setPaid(true);
       // customerOrderService.saveCustomerOrder(customerOrder,model);
        emailService.sendPaymentConfirmationEmail(customerOrder);

        return "redirect:/customerOrders";
    }




}
