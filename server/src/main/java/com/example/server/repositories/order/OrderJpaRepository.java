package com.example.server.repositories.order;


import com.example.server.models.Appeal;
import com.example.server.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderJpaRepository extends JpaRepository<Order, Long> {

    Order getByOrderId(Long orderId);

    Order findByOrderId(Long orderId);
}