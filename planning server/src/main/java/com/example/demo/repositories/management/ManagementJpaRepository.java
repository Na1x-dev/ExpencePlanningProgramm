package com.example.demo.repositories.management;

import
        com.example.demo.models.Management;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagementJpaRepository extends JpaRepository<Management, Long> {

}