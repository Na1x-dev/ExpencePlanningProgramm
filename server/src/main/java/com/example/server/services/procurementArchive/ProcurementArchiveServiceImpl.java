package com.example.server.services.procurementArchive;

import com.example.server.models.ProcurementArchive;
import com.example.server.repositories.procurementArchive.ProcurementArchiveJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcurementArchiveServiceImpl implements ProcurementArchiveService {

    @Autowired
    ProcurementArchiveJpaRepository procurementArchiveJpaRepository;

    @Override
    public ProcurementArchive create(ProcurementArchive procurementArchive) {
        return procurementArchiveJpaRepository.save(procurementArchive);
    }

    @Override
    public List<ProcurementArchive> readAll() {
        return procurementArchiveJpaRepository.findAll();
    }


    @Override
    public boolean delete(Long id) {
        if (procurementArchiveJpaRepository.existsById(id)) {
            procurementArchiveJpaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean update(Long id, ProcurementArchive procurementArchive) {
        if (procurementArchiveJpaRepository.existsById(id)) {
            procurementArchive.setProcurementArchiveId(id);
            procurementArchiveJpaRepository.save(procurementArchive);
            return true;
        }
        return false;
    }

//    @Override
//    public ProcurementArchive readById(Long procurementArchiveId) {
//        return procurementArchiveJpaRepository.getByProcurementArchiveId(procurementArchiveId);
//    }

}
