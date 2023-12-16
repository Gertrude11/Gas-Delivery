package com.Gertrude.Final.Project.services;

import com.Gertrude.Final.Project.model.Payment;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PaymentService {
    List<Payment> getAllPayments();
    void savePayment(Payment payment, Model model);
    void updatePayment(Payment payment);
    Optional<Payment> findByID(UUID id);
    void deleteById(UUID id);
    long countPayments();
}
