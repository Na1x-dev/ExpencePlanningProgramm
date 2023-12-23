package com.example.server.repositories.status;

import com.example.server.models.Appeal;
import com.example.server.models.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusJpaRepository extends JpaRepository<Status, Long> {

    Status getByStatusName(String statusName);

    Status findByStatusId(Long statusId);
}