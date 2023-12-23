package com.example.server.services.position;


import com.example.server.models.Appeal;
import com.example.server.models.Position;

import java.util.List;

public interface PositionService {
    Position create(Position position);

    List<Position> readAll();

    boolean delete(Long id);

    boolean update(Long id, Position position);


    //    Position readByTitle(String title);
    Position read(Long positionId);

    Position readById(Long positionId);
}
