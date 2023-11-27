package com.example.demo.repositories.application;

import com.example.demo.models.Application;
import com.example.demo.models.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationJpaRepository extends JpaRepository<Application, Long> {


}