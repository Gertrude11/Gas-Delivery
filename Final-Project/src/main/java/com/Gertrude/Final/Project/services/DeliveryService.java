package com.Gertrude.Final.Project.services;

import com.Gertrude.Final.Project.model.Delivery;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DeliveryService {
    List<Delivery> getAllDeliveries();
    void saveDelivery(Delivery delivery);
    void updateDelivery(Delivery delivery);
    Optional<Delivery> findByID(UUID id);
    void deleteById(UUID id);
}
