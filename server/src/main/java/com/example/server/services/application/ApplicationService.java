package com.example.server.services.application;

import com.example.server.models.Appeal;
import com.example.server.models.Application;


import java.util.List;

public interface ApplicationService {
    Application create(Application application);

    List<Application> readAll();

    boolean delete(Long id);

    Application read(Long applicationId);
    boolean update(Long id, Application application);

}
