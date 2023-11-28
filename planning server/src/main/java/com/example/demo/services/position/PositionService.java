package com.example.demo.services.position;


import com.example.demo.models.Position;

import java.util.List;

public interface PositionService {
    Position create(Position position);

    List<Position> readAll();

    boolean delete(Long id);

    boolean update(Long id, Position position);


//    Position readByTitle(String title);

    Position readById(Long positionId);
}
