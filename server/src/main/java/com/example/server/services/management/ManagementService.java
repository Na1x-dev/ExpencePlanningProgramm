package com.example.server.services.management;

import com.example.server.models.Appeal;
import com.example.server.models.Management;

import java.util.List;

public interface ManagementService {
    Management create(Management management);
    List<Management> readAll();
     boolean update(Long id, Management management);
    boolean delete(Long id);

    Management read(Long managementId);
//    Management readById(Long managementId);
}
