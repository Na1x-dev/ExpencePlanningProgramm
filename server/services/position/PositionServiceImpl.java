package com.example.server.services.position;

import com.example.server.models.Position;
import com.example.server.repositories.position.PositionJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionServiceImpl implements PositionService {

    @Autowired
    PositionJpaRepository positionJpaRepository;

    @Override
    public Position create(Position position) {
        positionJpaRepository.save(position);
        return position;
    }

    @Override
    public List<Position> readAll() {
        return positionJpaRepository.findAll();
    }

    @Override
    public boolean delete(Long id) {
        if (positionJpaRepository.existsById(id)) {
            positionJpaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean update(Long id, Position position) {
        if (positionJpaRepository.existsById(id)) {
            position.setPositionId(id);
            positionJpaRepository.save(position);
            return true;
        }
        return false;
    }

//    @Override
//    public Position readByName(String title) {
//        return positionJpaRepository.getByTitle(title);
//    }

    @Override
    public Position readById(Long positionId) {
        return positionJpaRepository.getByPositionId(positionId);
    }
}
