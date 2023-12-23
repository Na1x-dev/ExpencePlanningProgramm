package com.example.server.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import lombok.Data;


@Data
@JsonIgnoreProperties("hibernateLazyInitializer")
@Entity
@Table(name = "budget")
public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "budget_id")
    private Long budgetId;

    @ManyToOne
    @JoinColumn(name = "management_id",referencedColumnName = "management_id")
    private Management management;

    @Column(name = "budget_category1")
    private Double budgetCategory1;

    @Column(name = "budget_category2")
    private Double budgetCategory2;

    @Column(name = "budget_category3")
    private Double budgetCategory3;

    @Column(name = "final_budget")
    private Double finalBudget;

    public Budget(){
        budgetCategory1 = 0.0;
        budgetCategory2 = 0.0;
        budgetCategory3 = 0.0;
        finalBudget = 0.0;
    }

    @Override
    public String toString() {
        return "Budget{" +
                "budgetId=" + budgetId +
                ", management=" + management +
                ", budgetCategory1=" + budgetCategory1 +
                ", budgetCategory2=" + budgetCategory2 +
                ", budgetCategory3=" + budgetCategory3 +
                ", finalBudget=" + finalBudget +
                '}';
    }

    public void setBudgetId(Long id) {
    budgetId = id;
    }
}
