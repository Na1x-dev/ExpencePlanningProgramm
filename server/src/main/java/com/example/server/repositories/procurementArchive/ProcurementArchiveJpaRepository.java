package com.example.server.repositories.procurementArchive;

import com.example.server.models.Appeal;
import com.example.server.models.ProcurementArchive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcurementArchiveJpaRepository extends JpaRepository<ProcurementArchive, Long> {

    ProcurementArchive findByProcurementId(Long procurementId);
}