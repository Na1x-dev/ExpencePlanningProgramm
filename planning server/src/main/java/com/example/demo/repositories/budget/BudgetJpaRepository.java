package com.example.demo.repositories.budget;


import com.example.demo.models.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BudgetJpaRepository extends JpaRepository<Budget, Long> {

}