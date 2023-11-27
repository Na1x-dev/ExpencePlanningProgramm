package com.example.demo.services.delivery;

import com.example.demo.models.Delivery;
import com.example.demo.repositories.delivery.DeliveryJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryServiceImpl implements DeliveryService {

    @Autowired
    DeliveryJpaRepository deliveryJpaRepository;

    @Override
    public Delivery create(Delivery delivery) {
        return deliveryJpaRepository.save(delivery);
    }

    @Override
    public List<Delivery> readAll() {
        return deliveryJpaRepository.findAll();
    }

    @Override
    public boolean update(Long id, Delivery delivery) {
        if (deliveryJpaRepository.existsById(id)) {
            delivery.setDeliveryId(id);
            deliveryJpaRepository.save(delivery);
            return true;
        }
        return false;
    }


    @Override
    public boolean delete(Long id) {
        if (deliveryJpaRepository.existsById(id)) {
            deliveryJpaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Delivery readById(Long deliveryId) {
        return deliveryJpaRepository.getByDeliveryId(deliveryId);
    }

}
