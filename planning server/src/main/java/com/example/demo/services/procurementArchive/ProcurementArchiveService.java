package com.example.demo.services.procurementArchive;

import com.example.demo.models.ProcurementArchive;

import java.util.List;

public interface ProcurementArchiveService {
    ProcurementArchive create(ProcurementArchive procurementArchive);

    List<ProcurementArchive> readAll();


    boolean delete(Long id);

    boolean update(Long id, ProcurementArchive procurementArchive);

//    ProcurementArchive readById(Long procurementArchiveId);
}
