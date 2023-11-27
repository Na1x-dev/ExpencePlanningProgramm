package com.example.demo.services.order;

import com.example.demo.models.Order;

import java.util.List;

public interface OrderService {
    Order create(Order order);

    List<Order> readAll();




     boolean update(Long id, Order order);



    boolean delete(Long id);


    Order readById(Long orderId);
}
