package com.example.demo.repositories.shift;

import com.example.demo.models.Shift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShiftJpaRepository extends JpaRepository<Shift, Long> {


}