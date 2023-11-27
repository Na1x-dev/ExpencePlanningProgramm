package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NonNull;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "couriers")
@JsonIgnoreProperties("hibernateLazyInitializer")
public class Courier {
    @Id
    @Column(name = "courier_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long courierId;

    @Column(name = "surname")
    @NonNull
    String surname;

    @Column(name = "name")
    @NonNull
    String name;

    @Column(name = "patronymic")
    @NonNull
    String patronymic;

    @Column(name = "passport_number")
    @NonNull
    String passportNumber;

    @Column(name = "phone")
    @NonNull
    String phone;

    @Column(name = "organization_title")
    @NonNull
    String organizationTitle;

    @Column(name = "organization_phone")
    @NonNull
    String organizationPhone;

    public Courier() {
    }

    public String getFIO(){
        return surname + " " + name.charAt(0) + "." + patronymic.charAt(0) + ".";
    }
}
