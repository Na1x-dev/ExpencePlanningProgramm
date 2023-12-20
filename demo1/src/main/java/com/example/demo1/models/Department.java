package com.example.demo1.models;


import lombok.*;

@Data
public class Department implements Model {
    private Long departmentId;

    private String departmentName;

    private Management management;

    public Department(){
        departmentName = "";
        management = new Management();
    }

    @Override
    public String toString() {
        return "Department{" +
                "departmentId=" + departmentId +
                ", departmentName='" + departmentName + '\'' +
                ", management=" + management +
                '}';
    }


}