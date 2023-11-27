package com.example.demo.repositories.supplier;

import com.example.demo.models.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierJpaRepository extends JpaRepository<Supplier, Long> {
}