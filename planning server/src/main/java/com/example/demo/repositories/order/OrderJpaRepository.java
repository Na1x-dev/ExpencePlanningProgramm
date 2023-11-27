package com.example.demo.repositories.order;


import com.example.demo.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderJpaRepository extends JpaRepository<Order, Long> {

    Order getByOrderId(Long orderId);
}