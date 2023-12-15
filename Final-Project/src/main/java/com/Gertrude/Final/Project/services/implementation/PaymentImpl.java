package com.Gertrude.Final.Project.services.implementation;

import com.Gertrude.Final.Project.model.CustomerOrder;
import com.Gertrude.Final.Project.model.Payment;
import com.Gertrude.Final.Project.repository.CustomerOrderRepo;
import com.Gertrude.Final.Project.repository.CylinderRepo;
import com.Gertrude.Final.Project.repository.PaymentRepo;
import com.Gertrude.Final.Project.services.CustomerOrderService;
import com.Gertrude.Final.Project.services.PaymentService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PaymentImpl implements PaymentService {
    @Autowired
    private PaymentRepo repo;
    @Autowired
    private CustomerOrderService customerOrderService;

    @Override
    public List<Payment> getAllPayments() {
        return repo.findAll();
    }

    @Override
    public void savePayment(Payment payment, Model model) {
        payment.setStatus(true);
        CustomerOrder customerOrder = payment.getCustomerOrder();

        customerOrder.setPaid(true);
        //customerOrderService.saveCustomerOrder(customerOrder,model);

        repo.save(payment);
    }

    @Override
    public void updatePayment(Payment payment) {
        repo.save(payment);
    }

    @Override
    public Optional<Payment> findByID(UUID id) {
        return repo.findById(id);
    }

    @Override
    public void deleteById(UUID id) {
        repo.deleteById(id);
    }


}
