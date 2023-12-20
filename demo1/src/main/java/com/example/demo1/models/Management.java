package com.example.demo1.models;

import lombok.Data;


@Data
public class Management implements Model {
    private Long managementId;

    private String managementName;

    public Management() {
        managementName = "";
    }

    @Override
    public String toString() {
        return "Management{" +
                "managementId=" + managementId +
                ", managementName='" + managementName + '\'' +
                '}';
    }
}
