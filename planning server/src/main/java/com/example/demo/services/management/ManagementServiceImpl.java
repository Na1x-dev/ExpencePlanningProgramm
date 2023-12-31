package com.example.demo.services.management;

import com.example.demo.models.Management;
import com.example.demo.repositories.management.ManagementJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagementServiceImpl implements ManagementService {

    @Autowired
    ManagementJpaRepository managementJpaRepository;

    @Override
    public Management create(Management management) {
        return managementJpaRepository.save(management);
    }

    @Override
    public List<Management> readAll() {
        return managementJpaRepository.findAll();
    }

    @Override
    public boolean update(Long id, Management management) {
        if (managementJpaRepository.existsById(id)) {
            management.setManagementId(id);
            managementJpaRepository.save(management);
            return true;
        }
        return false;
    }


    @Override
    public boolean delete(Long id) {
        if (managementJpaRepository.existsById(id)) {
            managementJpaRepository.deleteById(id);
            return true;
        }
        return false;
    }

//    @Override
//    public Management readById(Long managementId) {
//        return managementJpaRepository.getByManagementId(managementId);
//    }

}
