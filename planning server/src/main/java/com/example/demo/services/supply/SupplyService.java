package com.example.demo.services.supply;

import java.util.List;

public interface SupplyService {
    Supply create(Supply supply);

    List<Supply> readAll();


    boolean delete(Long id);

    boolean update(Long id, Supply supply);

    Supply readById(Long supplyId);
}
