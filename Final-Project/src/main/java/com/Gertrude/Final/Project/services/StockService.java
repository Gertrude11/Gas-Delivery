package com.Gertrude.Final.Project.services;

import com.Gertrude.Final.Project.model.Stock;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StockService {
    List<Stock> getAllStocks();
    void saveStock(Stock stock);
    void updateStock(Stock stock);

    Optional<Stock> findByID(UUID id);

    void deleteById(UUID id);
}
