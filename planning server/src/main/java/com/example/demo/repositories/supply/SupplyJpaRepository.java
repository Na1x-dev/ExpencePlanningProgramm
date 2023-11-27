package com.example.demo.repositories.supply;


import com.example.demo.models.Supply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplyJpaRepository extends JpaRepository<Supply, Long> {

    Supply getBySupplyId(Long supplyId);
}