package com.example.server.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Data
@JsonIgnoreProperties("hibernateLazyInitializer")
@Entity
@Table(name = "managements")
public class Management {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "management_id")
    private Long managementId;

    @Column(name = "management_name")
    private String managementName;

    @ManyToOne
    @JoinColumn(name = "budget_id",referencedColumnName = "budget_id")
    private Budget budget;

    public Management() {
        managementName = "";
    }

    @Override
    public String toString() {
        return "Management{" +
                "managementId=" + managementId +
                ", managementName='" + managementName + '\'' +
                ", budget=" + budget +
                '}';
    }
}
