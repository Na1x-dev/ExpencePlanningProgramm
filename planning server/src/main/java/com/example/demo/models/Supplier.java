package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NonNull;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "suppliers")
@JsonIgnoreProperties("hibernateLazyInitializer")
public class Supplier {
    @Id
    @Column(name="supplier_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long supplierId;

    @NonNull
    @Column(name="unp")
    Integer unp;

    @NonNull
    @Column(name="title")
    String title;

    @NonNull
    @Column(name="phone")
    String phone;

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false, referencedColumnName = "country_id")
    @NonNull
    Country country;

    public Supplier() {
    }

}
