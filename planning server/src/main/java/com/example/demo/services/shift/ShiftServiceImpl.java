package com.example.demo.services.shift;

import com.example.demo.models.Shift;
import com.example.demo.repositories.shift.ShiftJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShiftServiceImpl implements ShiftService {

    @Autowired
    ShiftJpaRepository shiftJpaRepository;

    @Override
    public Shift create(Shift shift) {
        return shiftJpaRepository.save(shift);
    }

    @Override
    public List<Shift> readAll() {
        return shiftJpaRepository.findAll();
    }


    @Override
    public boolean delete(Long id) {
        if (shiftJpaRepository.existsById(id)) {
            shiftJpaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean update(Long id, Shift shift) {
        if (shiftJpaRepository.existsById(id)) {
            shift.setShiftId(id);
            shiftJpaRepository.save(shift);
            return true;
        }
        return false;
    }


}
