package com.example.server.services.department;

import com.example.server.models.Department;
import com.example.server.repositories.department.DepartmentJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    DepartmentJpaRepository departmentJpaRepository;

    @Override
    public Department create(Department department) {
        return departmentJpaRepository.save(department);
    }

    @Override
    public List<Department> readAll() {
        return departmentJpaRepository.findAll();
    }

    @Override
    public boolean update(Long id, Department department) {
        if (departmentJpaRepository.existsById(id)) {
            department.setDepartmentId(id);
            departmentJpaRepository.save(department);
            return true;
        }
        return false;
    }


    @Override
    public boolean delete(Long id) {
        if (departmentJpaRepository.existsById(id)) {
            departmentJpaRepository.deleteById(id);
            return true;
        }
        return false;
    }

//    @Override
//    public Department readById(Long departmentId) {
//        return departmentJpaRepository.getByDepartmentId(departmentId);
//    }

}
