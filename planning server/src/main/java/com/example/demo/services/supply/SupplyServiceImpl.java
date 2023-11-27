package com.example.demo.services.supply;

import com.example.demo.models.Supply;
import com.example.demo.repositories.supply.SupplyJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplyServiceImpl implements SupplyService {

    @Autowired
    SupplyJpaRepository supplyJpaRepository;

    @Override
    public Supply create(Supply supply) {
        return supplyJpaRepository.save(supply);
    }

    @Override
    public List<Supply> readAll() {
        return supplyJpaRepository.findAll();
    }

    @Override
    public boolean delete(Long id) {
        if (supplyJpaRepository.existsById(id)) {
            supplyJpaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean update(Long id, Supply supply) {
        if (supplyJpaRepository.existsById(id)) {
            supply.setSupplyId(id);
            supplyJpaRepository.save(supply);
            return true;
        }
        return false;
    }

    @Override
    public Supply readById(Long supplyId) {
        return supplyJpaRepository.getBySupplyId(supplyId);
    }

}
