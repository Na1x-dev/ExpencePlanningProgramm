package com.example.demo.services.budget;

import com.example.demo.models.Budget;
import com.example.demo.repositories.budget.BudgetJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BudgetServiceImpl implements BudgetService {

    @Autowired
    BudgetJpaRepository budgetJpaRepository;

    @Override
    public Budget create(Budget budget) {
        return budgetJpaRepository.save(budget);
    }

    @Override
    public List<Budget> readAll() {
        return budgetJpaRepository.findAll();
    }

    @Override
    public boolean delete(Long id) {
        if (budgetJpaRepository.existsById(id)) {
            budgetJpaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean update(Long id, Budget budget) {
        if (budgetJpaRepository.existsById(id)) {
            budget.setBudgetId(id);
            budgetJpaRepository.save(budget);
            return true;
        }
        return false;
    }
}
