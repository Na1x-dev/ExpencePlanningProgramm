package com.example.demo.repositories.delivery;

import com.example.demo.models.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliveryJpaRepository extends JpaRepository<Delivery, Long> {

    Delivery getByDeliveryId(Long deliveryId);
}