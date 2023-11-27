package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@JsonIgnoreProperties("hibernateLazyInitializer")
@Entity
@Data
@Table(name = "departments")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id")
    private Integer departmentId;

    @Column(name = "department_name")
    private String departmentName;

    @ManyToOne
    @JoinColumn(name = "management_id", referencedColumnName = "management_id")
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


    // Геттеры и сеттеры
}