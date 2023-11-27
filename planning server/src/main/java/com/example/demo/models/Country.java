package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NonNull;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.*;

@Entity
@Data
@Table(name = "countries")
@JsonIgnoreProperties("hibernateLazyInitializer")
public class Country {
    @Id
    @Column(name = "country_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long countryId;

    @NonNull
    @Column(name = "title")
    String title;


    public Country() {
    }

}
