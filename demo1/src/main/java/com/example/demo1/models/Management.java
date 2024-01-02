package com.example.demo1.models;

import lombok.Data;


@Data
public class Management implements Model {

    private Long managementId;



    private String managementName;

    private Budget budget;

    public Management() {
        budget = null;
        managementName = "";
    }

    @Override
    public String toString() {
        return "Management{" +
                "managementId=" + managementId +
                ", budget=" + budget +
                ", managementName='" + managementName + '\'' +
                '}';
    }
}
