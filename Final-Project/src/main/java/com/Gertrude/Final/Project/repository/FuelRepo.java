package com.Gertrude.Final.Project.repository;

import com.Gertrude.Final.Project.model.Fuel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FuelRepo extends JpaRepository<Fuel, UUID> {

}
