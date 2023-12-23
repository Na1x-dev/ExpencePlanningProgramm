package com.example.server.services.appeal;

import com.example.server.models.Appeal;

import java.util.List;

public interface AppealService {
    Appeal create(Appeal appeal);

    List<Appeal> readAll();


    boolean delete(Long id);

    boolean update(Long id, Appeal appeal);

    Appeal read(Long appealId);

    List<Appeal> findByUserName(String userName);
}
