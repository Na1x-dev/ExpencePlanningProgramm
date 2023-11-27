package com.example.demo.repositories.delivery;

import com.example.demo.models.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentJpaRepository extends JpaRepository<Department, Long> {

}