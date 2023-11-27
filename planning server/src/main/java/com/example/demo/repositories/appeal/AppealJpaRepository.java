package com.example.demo.repositories.appeal;

import com.example.demo.models.Appeal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppealJpaRepository extends JpaRepository<Appeal, Long> {

}