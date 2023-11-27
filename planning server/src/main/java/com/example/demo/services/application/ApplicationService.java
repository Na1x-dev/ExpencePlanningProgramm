package com.example.demo.services.application;

import com.example.demo.models.Application;


import java.util.List;

public interface ApplicationService {
    Application create(Application application);

    List<Application> readAll();

    boolean delete(Long id);

    boolean update(Long id, Application application);

}
