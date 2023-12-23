package com.example.server.services.budget;

import com.example.server.models.Appeal;
import com.example.server.models.Budget;


import java.util.List;

public interface BudgetService {
    Budget create(Budget country);

    List<Budget> readAll();

    boolean delete(Long id);

    Budget read(Long budgetId);
    boolean update(Long id, Budget budget);
}
