package com.example.demo.repositories.status;

import com.example.demo.models.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusJpaRepository extends JpaRepository<Status, Long> {

    Status getByTitle(String title);
}