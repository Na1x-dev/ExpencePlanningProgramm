package com.example.demo.services.appeal;

import com.example.demo.models.Appeal;

import java.util.List;

public interface AppealService {
    Appeal create(Appeal appeal);

    List<Appeal> readAll();


    boolean delete(Long id);

    boolean update(Long id, Appeal appeal);


}
