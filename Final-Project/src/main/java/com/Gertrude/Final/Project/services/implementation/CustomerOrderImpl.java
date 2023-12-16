package com.Gertrude.Final.Project.services.implementation;

import com.Gertrude.Final.Project.model.CustomerOrder;
import com.Gertrude.Final.Project.model.Cylinder;
import com.Gertrude.Final.Project.model.InsufficientQuantityException;
import com.Gertrude.Final.Project.repository.CustomerOrderRepo;
import com.Gertrude.Final.Project.repository.CylinderRepo;
import com.Gertrude.Final.Project.services.CustomerOrderService;
import com.Gertrude.Final.Project.services.CylinderService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerOrderImpl implements CustomerOrderService {
    @Autowired
    private CustomerOrderRepo repo;

    @Autowired
    CylinderService cylinderService;




    @Override
    public List<CustomerOrder> getAllCustomerOrders() {
        return repo.findAll();
    }

    @Override
    public void saveCustomerOrder(CustomerOrder customerOrder, Model model ) {
        try {
        //customerOrder.setPaid(false);

      //  customerOrder.processOrder();
            processOrder(customerOrder);

        repo.save(customerOrder);
    } catch (InsufficientQuantityException e) {
        // Handle the exception, e.g., by providing feedback to the user
        // You can customize this part based on your application's needs
            model.addAttribute("errorMessage", e.getMessage());

        System.out.println("Error: " + e.getMessage());
        // You can also add the error message to a model attribute and return it to the view
    }
    }


    @Override
    public void updateCustomerOrder(CustomerOrder customerOrder) {
        repo.save(customerOrder);
    }

    @Override
    public Optional<CustomerOrder> findByID(UUID id) {
        return repo.findById(id);
    }

    @Override
    public void deleteById(UUID id) {
        repo.deleteById(id);
    }

    @Override
    @Transactional
    public void processOrder(CustomerOrder customerOrder) {

        List<Cylinder> cylinders = customerOrder.getCylinders();
        int orderQuantity = customerOrder.getQuantity();



        for (Cylinder cylinder : cylinders) {
            cylinderService.updateQuantityAndTotalCost(orderQuantity,cylinder);
        }
     //   repo.save(customerOrder);

    }

    @Override
    public long countOrder() {
        return repo.count();
    }

//    @Override
//    public CustomerOrder placeOrder(CustomerOrder customerOrder) {
//        List<Cylinder> cylinders = customerOrder.getCylinders();
//
//        // Delegate the business logic to the separate service
//        cylinderService.checkAndPlaceOrder(customerOrder, cylinders);
//
//        // Save the order after updating cylinders
//        CustomerOrder savedOrder = repo.save(customerOrder);
//
//        // Save the updated cylinders to the database
//        for (Cylinder cylinder : cylinders) {
//            cylinderRepo.save(cylinder);
//        }
//
//        return savedOrder;
//    }


//    public void updateQuantityAndTotalCost(int orderQuantity) {
//        if (quantity < orderQuantity) {
//            throw new InsufficientQuantityException("Order quantity is greater than available quantity in the cylinder.");
//        }
//
//        quantity -= orderQuantity;
//        total_cost = total_cost.subtract(price.multiply(BigDecimal.valueOf(orderQuantity)));
//    }


}
