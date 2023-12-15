package com.Gertrude.Final.Project.repository;

import com.Gertrude.Final.Project.model.Cylinder;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CylinderRepo extends JpaRepository<Cylinder, UUID> {
    Cylinder findCylinderByCode(String code);

   // Cylinder deleteById(UUID id);
}
