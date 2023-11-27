package com.example.demo.services.shift;

import com.example.demo.models.Shift;


import java.util.List;

public interface ShiftService {
    Shift create(Shift shift);

    List<Shift> readAll();



    boolean delete(Long id);

    boolean update(Long id, Shift shift);
}
