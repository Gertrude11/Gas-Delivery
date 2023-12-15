package com.Gertrude.Final.Project.repository;

import com.Gertrude.Final.Project.model.Cylinder;
import com.Gertrude.Final.Project.model.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DeliveryRepo extends JpaRepository<Delivery, UUID> {
}
