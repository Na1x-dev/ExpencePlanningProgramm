package com.example.server.repositories.application;

import com.example.server.models.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ApplicationJpaRepository extends JpaRepository<Application, Long> {


}