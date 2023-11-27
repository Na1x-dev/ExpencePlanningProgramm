package com.example.demo.services.order;

import com.example.demo.models.Order;
import com.example.demo.repositories.order.OrderJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderJpaRepository orderJpaRepository;

    @Override
    public Order create(Order order) {
        return orderJpaRepository.save(order);
    }

    @Override
    public List<Order> readAll() {
        return orderJpaRepository.findAll();
    }

    @Override
    public boolean update(Long id, Order order) {
        if (orderJpaRepository.existsById(id)) {
            order.setOrderId(id);
            orderJpaRepository.save(order);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        if (orderJpaRepository.existsById(id)) {
            orderJpaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Order readById(Long orderId) {
        return orderJpaRepository.getByOrderId(orderId);
    }
}
