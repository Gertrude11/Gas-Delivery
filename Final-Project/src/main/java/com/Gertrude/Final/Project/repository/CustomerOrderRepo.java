package com.Gertrude.Final.Project.repository;

import com.Gertrude.Final.Project.model.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface CustomerOrderRepo extends JpaRepository<CustomerOrder, UUID> {

}
