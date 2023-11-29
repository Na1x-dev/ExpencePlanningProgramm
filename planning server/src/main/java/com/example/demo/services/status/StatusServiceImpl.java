package com.example.demo.services.status;

import com.example.demo.models.Status;
import com.example.demo.repositories.status.StatusJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusServiceImpl implements StatusService {

    @Autowired
    StatusJpaRepository statusJpaRepository;

    @Override
    public Status create(Status status) {
   
        return statusJpaRepository.save(status);
    }

    @Override
    public List<Status> readAll() {
        return statusJpaRepository.findAll();
    }

    @Override
    public boolean delete(Long id) {
        if (statusJpaRepository.existsById(id)) {
            statusJpaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean update(Long id, Status status) {
        if (statusJpaRepository.existsById(id)) {
            status.setStatusId(id);
            statusJpaRepository.save(status);
            return true;
        }
        return false;
    }

    @Override
    public Status readByTitle(String title) {
        return statusJpaRepository.getByStatusName(title);
    }

}
