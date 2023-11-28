package com.example.demo.services.budget;

import com.example.demo.models.Budget;


import java.util.List;

public interface BudgetService {
    Budget create(Budget country);

    List<Budget> readAll();

    boolean delete(Long id);

    boolean update(Long id, Budget budget);
}
