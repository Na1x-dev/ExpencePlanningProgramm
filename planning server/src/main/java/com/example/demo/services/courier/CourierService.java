package com.example.demo.services.courier;

import com.example.demo.models.Courier;

import java.util.List;

public interface CourierService {
    Courier create(Courier courier);

    List<Courier> readAll();


    boolean delete(Long id);

    boolean update(Long id, Courier courier);


}
