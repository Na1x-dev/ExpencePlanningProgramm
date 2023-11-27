package com.example.demo.services.courier;

import com.example.demo.models.Courier;
import com.example.demo.repositories.courier.CourierJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourierServiceImpl implements CourierService {

    @Autowired
    CourierJpaRepository courierJpaRepository;

    @Override
    public Courier create(Courier courier) {
        return courierJpaRepository.save(courier);
    }

    @Override
    public List<Courier> readAll() {
        return courierJpaRepository.findAll();
    }

    @Override
    public boolean delete(Long id) {
        if (courierJpaRepository.existsById(id)) {
            courierJpaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean update(Long id, Courier courier) {
        if (courierJpaRepository.existsById(id)) {
            courier.setCourierId(id);
            courierJpaRepository.save(courier);
            return true;
        }
        return false;
    }


}
