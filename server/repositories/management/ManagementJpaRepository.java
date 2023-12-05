package com.example.server.repositories.management;

import
        com.example.server.models.Management;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagementJpaRepository extends JpaRepository<Management, Long> {

}