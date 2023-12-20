package com.example.demo1.models;

import lombok.Data;


@Data

public class Budget  implements Model{

    private Long budgetId;

    private Management management;

    private Double budgetCategory1;

    private Double budgetCategory2;

    private Double budgetCategory3;

    private Double finalBudget;

    public Budget(){
        management = new Management();
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

}
