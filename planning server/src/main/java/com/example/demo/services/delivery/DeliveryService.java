package com.example.demo.services.delivery;

import com.example.demo.models.Delivery;

import java.util.List;

public interface DeliveryService {
    Delivery create(Delivery delivery);
    List<Delivery> readAll();
     boolean update(Long id, Delivery delivery);
    boolean delete(Long id);


    Delivery readById(Long deliveryId);
}
