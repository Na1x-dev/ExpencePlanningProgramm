package com.example.demo.services.management;

import com.example.demo.models.Management;

import java.util.List;

public interface ManagementService {
    Management create(Management management);
    List<Management> readAll();
     boolean update(Long id, Management management);
    boolean delete(Long id);


//    Management readById(Long managementId);
}
