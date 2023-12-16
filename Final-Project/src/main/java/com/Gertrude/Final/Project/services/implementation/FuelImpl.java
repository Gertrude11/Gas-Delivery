package com.Gertrude.Final.Project.services.implementation;

import com.Gertrude.Final.Project.model.Fuel;
import com.Gertrude.Final.Project.repository.FuelRepo;
import com.Gertrude.Final.Project.services.FuelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FuelImpl implements FuelService {

    @Autowired
    private FuelRepo repo;
    @Override
    public List<Fuel> getAllFuels() {
        return repo.findAll();
    }

    @Override
    public void saveFuel(Fuel fuel) {
        repo.save(fuel);
    }

    @Override
    public void updateFuel(Fuel fuel) {
        repo.save(fuel);
    }

    @Override
    public Optional<Fuel> findByID(UUID id) {
        return repo.findById(id);
    }

    @Override
    public void deleteById(UUID id) {
        repo.deleteById(id);
    }

    @Override
    public long countFuels() {
        return repo.count();
    }
}
