package com.example.server.repositories.budget;


import com.example.server.models.Appeal;
import com.example.server.models.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BudgetJpaRepository extends JpaRepository<Budget, Long> {

    Budget findByBudgetId(Long budgetId);
}