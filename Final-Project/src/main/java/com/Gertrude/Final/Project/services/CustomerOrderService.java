package com.Gertrude.Final.Project.services;

import com.Gertrude.Final.Project.model.CustomerOrder;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerOrderService {
    List<CustomerOrder> getAllCustomerOrders();
    void saveCustomerOrder(CustomerOrder customerOrder, Model model);
    void updateCustomerOrder(CustomerOrder customerOrder);
    Optional<CustomerOrder> findByID(UUID id);
    void deleteById(UUID id);
    void processOrder(CustomerOrder customerOrder);
   // CustomerOrder placeOrder(CustomerOrder customerOrder);

}
