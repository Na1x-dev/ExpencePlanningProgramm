package com.example.server.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import lombok.*;


@JsonIgnoreProperties("hibernateLazyInitializer")
@Entity
@Data
@Table(name = "departments")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id")
    private Long departmentId;

    @Column(name = "department_name")
    private String departmentName;

    @ManyToOne
    @JoinColumn(name = "management_id", referencedColumnName = "management_id")
    private Management management;

    public Department(){
        departmentName = "";
    }

    @Override
    public String toString() {
        return "Department{" +
                "departmentId=" + departmentId +
                ", departmentName='" + departmentName + '\'' +
                ", management=" + management +
                '}';
    }

    public void setDepartmentId(Long id) {
        departmentId = id;
    }


    // Геттеры и сеттеры
}