package com.Gertrude.Final.Project.services.implementation;

import com.Gertrude.Final.Project.model.Delivery;
import com.Gertrude.Final.Project.repository.DeliveryRepo;
import com.Gertrude.Final.Project.services.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DeliveryImpl implements DeliveryService {
    @Autowired
    private DeliveryRepo repo;

    @Override
    public List<Delivery> getAllDeliveries() {
        return repo.findAll();
    }

    @Override
    public void saveDelivery(Delivery delivery) {
        repo.save(delivery);
    }

    @Override
    public void updateDelivery(Delivery delivery) {
        repo.save(delivery);
    }

    @Override
    public Optional<Delivery> findByID(UUID id) {
        return repo.findById(id);
    }

    @Override
    public void deleteById(UUID id) {
        repo.deleteById(id);
    }
}
