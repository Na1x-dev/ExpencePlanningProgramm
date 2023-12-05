package com.example.server.repositories.position;

import com.example.server.models.Application;
import com.example.server.models.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionJpaRepository extends JpaRepository<Position, Long> {

    Position getByPositionId(Long positionId);
}