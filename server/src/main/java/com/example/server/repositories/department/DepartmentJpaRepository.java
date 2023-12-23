package com.example.server.repositories.department;

import com.example.server.models.Appeal;
import com.example.server.models.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentJpaRepository extends JpaRepository<Department, Long> {

    Department findByDepartmentId(Long departmentId);
}