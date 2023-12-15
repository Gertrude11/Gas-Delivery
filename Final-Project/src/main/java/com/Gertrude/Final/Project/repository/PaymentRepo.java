package com.Gertrude.Final.Project.repository;

import com.Gertrude.Final.Project.model.Cylinder;
import com.Gertrude.Final.Project.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface PaymentRepo extends JpaRepository<Payment, UUID> {

//    @Modifying
//    @Query("UPDATE CustomerOrder co SET co.paid = 'PAID' WHERE co.id = :orderId")
//    void handleSuccessfulPayment(UUID orderId);
}
