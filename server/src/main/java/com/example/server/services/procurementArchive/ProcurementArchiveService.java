package com.example.server.services.procurementArchive;

import com.example.server.models.Appeal;
import com.example.server.models.ProcurementArchive;

import java.util.List;

public interface ProcurementArchiveService {
    ProcurementArchive create(ProcurementArchive procurementArchive);

    List<ProcurementArchive> readAll();


    boolean delete(Long id);

    ProcurementArchive read(Long procurementId);
    boolean update(Long id, ProcurementArchive procurementArchive);

//    ProcurementArchive readById(Long procurementArchiveId);
}
