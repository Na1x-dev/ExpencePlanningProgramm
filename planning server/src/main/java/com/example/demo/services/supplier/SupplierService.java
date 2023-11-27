package com.example.demo.services.supplier;


import com.example.demo.models.Supplier;

import java.util.List;

public interface SupplierService {
    Supplier create(Supplier supplier);

    List<Supplier> readAll();

    boolean delete(Long id);

    boolean update(Long id, Supplier supplier);
}
