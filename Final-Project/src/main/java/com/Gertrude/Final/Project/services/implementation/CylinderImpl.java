package com.Gertrude.Final.Project.services.implementation;

import com.Gertrude.Final.Project.model.CustomerOrder;
import com.Gertrude.Final.Project.model.Cylinder;
import com.Gertrude.Final.Project.model.InsufficientQuantityException;
import com.Gertrude.Final.Project.repository.CylinderRepo;
import com.Gertrude.Final.Project.services.CylinderService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CylinderImpl implements CylinderService {

    @Autowired
    private CylinderRepo repo;
    @Override
    public List<Cylinder> getAllCylinders() {
        return repo.findAll();
    }

    @Override
    public void saveCylinder(Cylinder cylinder) {
        repo.save(cylinder);
    }

    @Override
    public void updateCylinder(Cylinder cylinder) {
        repo.save(cylinder);
    }

    @Override
    public Optional<Cylinder> findByID(UUID id) {
        return repo.findById(id);
    }

    @Override
    public void deleteById(UUID id) {
       repo.deleteById(id);

    }

    @Override
    public Cylinder findCylinderByCode(String code) {
        return repo.findCylinderByCode(code);
    }

    @Override
    @Transactional
    public void updateQuantityAndTotalCost(int orderQuantity,Cylinder cylinder) {
//        if (cylinder.getQuantity() < orderQuantity) {
//            throw new InsufficientQuantityException("Order quantity is greater than available quantity in the stock.");
//        }
//        // Update the quantity
//        cylinder.setQuantity(cylinder.getQuantity() - orderQuantity);
//
//        // Update the total cost
//        BigDecimal orderTotalCost = cylinder.getPrice().multiply(BigDecimal.valueOf(orderQuantity));
//        cylinder.setTotal_cost(cylinder.getTotal_cost().subtract(orderTotalCost));

        // Log to check if the correct Cylinder object is being passed
        System.out.println("Updating Cylinder: " + cylinder);

        if (cylinder.getQuantity() < orderQuantity) {
            throw new InsufficientQuantityException("Order quantity is greater than available quantity in the stock.");
        }

        // Log to check the values before the update
        System.out.println("Before Update - Quantity: " + cylinder.getQuantity() + ", Total Cost: " + cylinder.getTotal_cost());

        // Update the quantity
        cylinder.setQuantity(cylinder.getQuantity() - orderQuantity);

        // Update the total cost
        BigDecimal orderTotalCost = cylinder.getPrice().multiply(BigDecimal.valueOf(orderQuantity));
        cylinder.setTotal_cost(cylinder.getTotal_cost().subtract(orderTotalCost));

        // Save the updated cylinder to the database
       // repo.save(cylinder);

        // Log to check the values after the update
        System.out.println("After Update - Quantity: " + cylinder.getQuantity() + ", Total Cost: " + cylinder.getTotal_cost());

    }

    @Override
    public long countCylinders() {
        return repo.count();
    }


}
