package com.example.demo1.models;

import lombok.Data;


@Data

public class Budget  implements Model{

    private Long budgetId;

    private Double finalBudget;

    private Double budgetCategory1;

    private Double budgetCategory2;

    private Double budgetCategory3;



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
                ", budgetCategory1=" + budgetCategory1 +
                ", budgetCategory2=" + budgetCategory2 +
                ", budgetCategory3=" + budgetCategory3 +
                ", finalBudget=" + finalBudget +
                '}';
    }

}
