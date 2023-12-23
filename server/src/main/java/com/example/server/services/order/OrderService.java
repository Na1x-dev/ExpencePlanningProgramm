package com.example.server.services.order;

import com.example.server.models.Appeal;
import com.example.server.models.Order;

import java.util.List;

public interface OrderService {
    Order create(Order order);

    List<Order> readAll();




     boolean update(Long id, Order order);


    Order read(Long orderId);
    boolean delete(Long id);


    Order readById(Long orderId);
}
