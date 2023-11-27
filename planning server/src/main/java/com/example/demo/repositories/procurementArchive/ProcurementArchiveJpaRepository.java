package com.example.demo.repositories.procurementArchive;

import com.example.demo.models.ProcurementArchive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcurementArchiveJpaRepository extends JpaRepository<ProcurementArchive, Long> {


}