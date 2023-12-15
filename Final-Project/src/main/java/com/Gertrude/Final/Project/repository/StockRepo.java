package com.Gertrude.Final.Project.repository;

import com.Gertrude.Final.Project.model.CustomerOrder;
import com.Gertrude.Final.Project.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StockRepo extends JpaRepository<Stock, UUID> {
}
