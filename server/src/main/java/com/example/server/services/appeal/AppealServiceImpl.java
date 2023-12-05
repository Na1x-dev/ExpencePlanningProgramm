package com.example.server.services.appeal;

import com.example.server.models.Appeal;
import com.example.server.repositories.appeal.AppealJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppealServiceImpl implements AppealService {

    @Autowired
    AppealJpaRepository appealJpaRepository;

    @Override
    public Appeal create(Appeal appeal) {
        return appealJpaRepository.save(appeal);
    }

    @Override
    public List<Appeal> readAll() {
        return appealJpaRepository.findAll();
    }

    @Override
    public boolean delete(Long id) {
        if (appealJpaRepository.existsById(id)) {
            appealJpaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean update(Long id, Appeal appeal) {
        if (appealJpaRepository.existsById(id)) {
            appeal.setAppealId(id);
            appealJpaRepository.save(appeal);
            return true;
        }
        return false;
    }


}
