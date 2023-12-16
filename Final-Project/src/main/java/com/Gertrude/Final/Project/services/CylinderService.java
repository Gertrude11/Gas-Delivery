package com.Gertrude.Final.Project.services;

import com.Gertrude.Final.Project.model.CustomerOrder;
import com.Gertrude.Final.Project.model.Cylinder;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CylinderService {
    List<Cylinder> getAllCylinders();
    void saveCylinder(Cylinder cylinder);
    void updateCylinder(Cylinder cylinder);
    Optional<Cylinder> findByID(UUID id);
    void deleteById(UUID id);
    Cylinder findCylinderByCode(String code);
    void updateQuantityAndTotalCost(int orderQuantity,Cylinder cylinder);
    long countCylinders();
}
