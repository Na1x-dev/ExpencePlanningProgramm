package com.example.demo.services.department;

import com.example.demo.models.Department;

import java.util.List;

public interface DepartmentService {
    Department create(Department department);
    List<Department> readAll();
     boolean update(Long id, Department department);
    boolean delete(Long id);


//    Department readById(Long departmentId);
}
