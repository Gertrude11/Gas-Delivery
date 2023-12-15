package com.Gertrude.Final.Project.services.implementation;

import com.Gertrude.Final.Project.model.Stock;
import com.Gertrude.Final.Project.repository.PaymentRepo;
import com.Gertrude.Final.Project.repository.StockRepo;
import com.Gertrude.Final.Project.services.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StockImpl implements StockService {

    @Autowired
    private StockRepo repo;
    @Override
    public List<Stock> getAllStocks() {
        return repo.findAll();
    }

    @Override
    public void saveStock(Stock stock) {
        repo.save(stock);

    }

    @Override
    public void updateStock(Stock stock) {
        repo.save(stock);
    }

    @Override
    public Optional<Stock> findByID(UUID id) {
        return repo.findById(id);
    }

    @Override
    public void deleteById(UUID id) {
        repo.deleteById(id);
    }
}
