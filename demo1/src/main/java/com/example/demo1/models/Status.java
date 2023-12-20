package com.example.demo1.models;

import lombok.Data;


@Data
public class Status implements Model {
    private Long statusId;

    private String statusName;

    public Status() {
        statusName = "";
    }

    public Status(String statusName) {
        this.statusName = statusName;
    }

    @Override
    public String toString() {
        return "Status{" +
                "statusId=" + statusId +
                ", statusName='" + statusName + '\'' +
                '}';
    }
}
