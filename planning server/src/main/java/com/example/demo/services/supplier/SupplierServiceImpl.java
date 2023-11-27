package com.example.demo.services.supplier;

import com.example.demo.models.Supplier;
import com.example.demo.repositories.supplier.SupplierJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    SupplierJpaRepository supplierJpaRepository;

    @Override
    public Supplier create(Supplier supplier) {
        return supplierJpaRepository.save(supplier);
    }

    @Override
    public List<Supplier> readAll() {
        return supplierJpaRepository.findAll();
    }

    @Override
    public boolean delete(Long id) {
        if (supplierJpaRepository.existsById(id)) {
            supplierJpaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean update(Long id, Supplier supplier) {
        if (supplierJpaRepository.existsById(id)) {
            supplier.setSupplierId(id);
            supplierJpaRepository.save(supplier);
            return true;
        }
        return false;
    }
}
