package com.example.server.services.status;

import com.example.server.models.Status;

import java.util.List;

public interface StatusService {
    Status create(Status status);

    List<Status> readAll();


    boolean delete(Long id);

    boolean update(Long id, Status status);

    Status readByTitle(String title);
}
