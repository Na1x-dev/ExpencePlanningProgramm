package com.example.server.repositories.appeal;

import com.example.server.models.Appeal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppealJpaRepository extends JpaRepository<Appeal, Long> {

}